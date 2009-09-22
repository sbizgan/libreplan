package org.navalplanner.web.planner.allocation;

import org.navalplanner.business.planner.entities.AggregateOfResourceAllocations;
import org.navalplanner.business.planner.entities.CalculatedValue;
import org.zkoss.zul.Intbox;

public class ResourceAllocationFormBinder {

    private Intbox assignedHoursComponent;
    private final ResourceAllocationsBeingEdited resourceAllocationsBeingEdited;
    private AggregateOfResourceAllocations aggregate;

    public ResourceAllocationFormBinder(
            ResourceAllocationsBeingEdited resourceAllocationsBeingEdited) {
        this.resourceAllocationsBeingEdited = resourceAllocationsBeingEdited;
        this.aggregate = this.resourceAllocationsBeingEdited
                .getInitialAggregate();
    }

    public void setAssignedHoursComponent(Intbox assignedHoursComponent) {
        this.assignedHoursComponent = assignedHoursComponent;
        this.assignedHoursComponent.setDisabled(resourceAllocationsBeingEdited
                .getCalculatedValue() == CalculatedValue.NUMBER_OF_HOURS);
        this.assignedHoursComponent.setValue(aggregate.getTotalHours());
    }

}
