package com.fengxudong.framework.mp;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class FrameworkTenantSqlParser extends TenantSqlParser {

    @Override
    protected void processPlainSelect(PlainSelect plainSelect, boolean addColumn) {
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            Table fromTable = (Table) fromItem;
            if (this.getTenantHandler().doTableFilter(fromTable.getName())) {
                // 过滤退出执行
                return;
            }
            Expression expression = plainSelect.getWhere();
            String whereString = expression.toString();
            if (StringUtils.isNotEmpty(whereString) && whereString.contains("org_id = ")) {
                return;
            }

            String columnName = getColumnName(expression);
            if ("id".equals(columnName) || "org_id".equals(columnName)) {
                return;
            }
            plainSelect.setWhere(builderExpression(expression, fromTable));
            if (addColumn) {
                plainSelect.getSelectItems().add(new SelectExpressionItem(new Column(this.getTenantHandler().getTenantIdColumn())));
            }
        } else {
            processFromItem(fromItem);
        }
        List<Join> joins = plainSelect.getJoins();
        if (joins != null && joins.size() > 0) {
            joins.forEach(j -> {
                processJoin(j);
                processFromItem(j.getRightItem());
            });
        }
    }

    @Override
    public void processUpdate(Update update) {
        List<Table> tableList = update.getTables();
        Assert.isTrue(null != tableList && tableList.size() < 2,
                "Failed to process multiple-table update, please exclude the statementId");
        Table table = tableList.get(0);
        if (this.getTenantHandler().doTableFilter(table.getName())) {
            // 过滤退出执行
            return;
        }
        Expression expression = update.getWhere();
        String whereStr = expression.toString();

        if (StringUtils.isNotEmpty(whereStr) && whereStr.contains("org_id = ")) {
            // 如果查询条件已经包含了org_id 直接退出
            return;
        }
        String columnName = getColumnName(expression);
        if ("id".equals(columnName) || "org_id".equals(columnName)) {
            return;
        }
        update.setWhere(this.andExpression(table, update.getWhere()));
    }

    @Override
    public void processInsert(Insert insert) {
        if (this.getTenantHandler().doTableFilter(insert.getTable().getName())) {
            // 过滤退出执行
            return;
        }
        // 如果列中已经包含了多租户列 不再注入
        List<Column> retList = insert.getColumns();
        for (Column column : retList) {
            if (column.getColumnName() != null && column.getColumnName().equals(this.getTenantHandler().getTenantIdColumn())) {
                return;
            }
        }

        insert.getColumns().add(new Column(this.getTenantHandler().getTenantIdColumn()));
        if (insert.getSelect() != null) {
            processPlainSelect((PlainSelect) insert.getSelect().getSelectBody(), true);
        } else if (insert.getItemsList() != null) {
            // fixed github pull/295
            ItemsList itemsList = insert.getItemsList();
            if (itemsList instanceof MultiExpressionList) {
                ((MultiExpressionList) itemsList).getExprList().forEach(el -> el.getExpressions().add(this.getTenantHandler().getTenantId()));
            } else {
                ((ExpressionList) insert.getItemsList()).getExpressions().add(this.getTenantHandler().getTenantId());
            }
        } else {
            throw ExceptionUtils.mpe("Failed to process multiple-table update, please exclude the tableName or statementId");
        }
    }

    private String getColumnName(Expression expression) {
        if (expression instanceof EqualsTo) {
            EqualsTo where = (EqualsTo) expression;
            Column leftExpression = (Column) where.getLeftExpression();
            return leftExpression.getColumnName();
        }
        return null;
    }
}
