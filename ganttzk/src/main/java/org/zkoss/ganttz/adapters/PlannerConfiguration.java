package org.zkoss.ganttz.adapters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.zkoss.ganttz.extensions.ICommand;
import org.zkoss.ganttz.extensions.ICommandOnTask;
import org.zkoss.ganttz.extensions.IContext;
import org.zkoss.ganttz.extensions.IContextWithPlannerTask;
import org.zkoss.ganttz.extensions.ITabFactory;

/**
 * A object that defines several extension points for gantt planner
 * @author Óscar González Fernández <ogonzalez@igalia.com>
 */
public class PlannerConfiguration<T> {

    private static class NullCommand<T> implements ICommand<T> {

        @Override
        public void doAction(IContext<T> context) {
            // do nothing
        }

        @Override
        public String getName() {
            return "";
        }

    }

    private static class NullCommandOnTask<T> implements ICommandOnTask<T> {

        @Override
        public void doAction(IContextWithPlannerTask<T> context, T task) {
            // do nothing
        }

        @Override
        public String getName() {
            return "";
        }

    }

    private IAdapterToTaskFundamentalProperties<T> adapter;

    private IStructureNavigator<T> navigator;

    private List<? extends T> data;

    private List<ICommand<T>> globalCommands = new ArrayList<ICommand<T>>();

    private List<ICommandOnTask<T>> commandsOnTasks = new ArrayList<ICommandOnTask<T>>();

    private ICommand<T> goingDownInLastArrowCommand = new NullCommand<T>();

    private ICommandOnTask<T> editTaskCommand = new NullCommandOnTask<T>();

    private List<ITabFactory<T>> tabFactories = new ArrayList<ITabFactory<T>>();

    public PlannerConfiguration(IAdapterToTaskFundamentalProperties<T> adapter,
            IStructureNavigator<T> navigator, List<? extends T> data) {
        this.adapter = adapter;
        this.navigator = navigator;
        this.data = data;
    }

    public IAdapterToTaskFundamentalProperties<T> getAdapter() {
        return adapter;
    }

    public IStructureNavigator<T> getNavigator() {
        return navigator;
    }

    public List<? extends T> getData() {
        return data;
    }

    public void addCommandOnTask(ICommandOnTask<T> commandOnTask) {
        Validate.notNull(commandOnTask);
        this.commandsOnTasks.add(commandOnTask);
    }

    public void addGlobalCommand(ICommand<T> command) {
        Validate.notNull(command);
        this.globalCommands.add(command);
    }

    public List<ICommandOnTask<T>> getCommandsOnTasks() {
        return Collections.unmodifiableList(commandsOnTasks);
    }

    public List<ICommand<T>> getGlobalCommands() {
        return Collections.unmodifiableList(globalCommands);
    }

    public ICommand<T> getGoingDownInLastArrowCommand() {
        return goingDownInLastArrowCommand;
    }

    public void setGoingDownInLastArrowCommand(
            ICommand<T> goingDownInLastArrowCommand) {
        Validate.notNull(goingDownInLastArrowCommand);
        this.goingDownInLastArrowCommand = goingDownInLastArrowCommand;
    }

    public ICommandOnTask<T> getEditTaskCommand() {
        return editTaskCommand;
    }

    public void setEditTaskCommand(ICommandOnTask<T> editTaskCommand) {
        Validate.notNull(editTaskCommand);
        this.editTaskCommand = editTaskCommand;
    }

    public void addTab(ITabFactory<T> tabCreator) {
        Validate.notNull(tabCreator);
        this.tabFactories.add(tabCreator);
    }

    public List<ITabFactory<T>> getTabFactories() {
        return Collections.unmodifiableList(tabFactories);
    }
}
