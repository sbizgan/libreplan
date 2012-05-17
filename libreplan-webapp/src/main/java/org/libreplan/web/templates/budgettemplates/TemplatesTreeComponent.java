/*
 * This file is part of LibrePlan
 *
 * Copyright (C) 2009-2010 Fundación para o Fomento da Calidade Industrial e
 *                         Desenvolvemento Tecnolóxico de Galicia
 * Copyright (C) 2010-2011 Igalia, S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.libreplan.web.templates.budgettemplates;

import static org.libreplan.web.I18nHelper._;

import java.util.ArrayList;
import java.util.List;

import org.libreplan.business.templates.entities.OrderElementTemplate;
import org.libreplan.business.trees.ITreeNode;
import org.libreplan.web.templates.budgettemplates.TemplatesTreeController.TemplatesTreeRenderer;
import org.libreplan.web.tree.TreeComponent;
import org.libreplan.web.tree.TreeController;
import org.zkoss.zul.Treeitem;

/**
 * Tree component for templates <br />
 * @author Óscar González Fernández <ogonzalez@igalia.com>
 */
public class TemplatesTreeComponent extends TreeComponent {

    private abstract class TemplatesTreeColumn extends Column {

        TemplatesTreeColumn(String label, String cssClass, String tooltip) {
            super(label, cssClass, tooltip);
        }

        TemplatesTreeColumn(String label, String cssClass) {
            super(label, cssClass);
        }

        public final <T extends ITreeNode<T>> void doCell(
                TreeController<T>.Renderer renderer,
                Treeitem item, T currentElement) {
            doCell(TemplatesTreeRenderer.class.cast(renderer), item,
                    OrderElementTemplate.class.cast(currentElement));
        }

        protected abstract void doCell(TemplatesTreeRenderer renderer,
                Treeitem item, OrderElementTemplate currentElement);

    }

    public String getAddElementLabel() {
        return _("Add");
    }

    public boolean isCreateTemplateEnabled() {
        return true;
    }

    public String getRemoveElementLabel() {
        return _("Delete Template element");
    }

    @Override
    public List<Column> getColumns() {
        List<Column> result = new ArrayList<Column>();

        result.add(new Column("", "",
                _("Position in the tree. (Drag and drop to move tasks)")) {

            @Override
            public <T extends ITreeNode<T>> void doCell(
                    TreeController<T>.Renderer renderer, Treeitem item,
                    T currentElement) {
                renderer.addSchedulingStateCell(currentElement);
            }

        });

        result.add(codeColumn);
        result.add(nameAndDescriptionColumn);
        result.add(new TemplatesTreeColumn(_("TOTAL"), "budget") {

            @Override
            protected void doCell(TemplatesTreeRenderer renderer,
                    Treeitem item, OrderElementTemplate currentElement) {
                renderer.addBudgetCell(currentElement);
            }

        });
        result.add(new TemplatesTreeColumn(_("Cost/Salary"), "budget") {

            @Override
            protected void doCell(TemplatesTreeRenderer renderer,
                    Treeitem item, OrderElementTemplate currentElement) {
                renderer.addCostSalaryCell(currentElement);
            }

        });
        result.add(new TemplatesTreeColumn(_("Type"), "budgettype") {

            @Override
            protected void doCell(TemplatesTreeRenderer renderer,
                    Treeitem item, OrderElementTemplate currentElement) {
                renderer.addBudgetLineTypeCell(currentElement);
            }

        });
        result.add(new TemplatesTreeColumn(_("Duration"), "units") {

            @Override
            protected void doCell(TemplatesTreeRenderer renderer,
                    Treeitem item, OrderElementTemplate currentElement) {
                renderer.addDurationCell(currentElement);
            }

        });
        result.add(new TemplatesTreeColumn(_("Quantity"), "units") {

            @Override
            protected void doCell(TemplatesTreeRenderer renderer,
                    Treeitem item, OrderElementTemplate currentElement) {
                renderer.addQuantityCell(currentElement);
            }

        });
        result.add(new TemplatesTreeColumn(_("Severance pay"), "budget") {

            @Override
            protected void doCell(TemplatesTreeRenderer renderer,
                    Treeitem item, OrderElementTemplate currentElement) {
                renderer.addIndemnizationSalaryCell(currentElement);
            }

        });
        result.add(new TemplatesTreeColumn(_("Holiday salary"), "budget") {

            @Override
            protected void doCell(TemplatesTreeRenderer renderer,
                    Treeitem item, OrderElementTemplate currentElement) {
                renderer.addHolidaySalaryCell(currentElement);
            }

        });
        result.add(operationsColumn);
        return result;
    }

}