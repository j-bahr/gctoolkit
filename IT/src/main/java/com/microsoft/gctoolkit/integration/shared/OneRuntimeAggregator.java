package com.microsoft.gctoolkit.integration.shared;

import com.microsoft.gctoolkit.aggregator.Aggregates;
import com.microsoft.gctoolkit.aggregator.Aggregator;
import com.microsoft.gctoolkit.aggregator.EventSource;
import com.microsoft.gctoolkit.event.jvm.JVMTermination;

@Aggregates({EventSource.G1GC,EventSource.GENERATIONAL,EventSource.SHENANDOAH,EventSource.ZGC})
public class OneRuntimeAggregator extends Aggregator<OneRuntimeReport> {
    /**
     * Subclass only.
     *
     * @param aggregation The Aggregation that {@literal @}Collates this Aggregator
     */
    public OneRuntimeAggregator(OneRuntimeReport aggregation) {
        super(aggregation);
        register(JVMTermination.class,this::record);
    }

    public void record(JVMTermination termination) {
        aggregation().terminate(termination.getEstimatedRuntimeDuration());
    }
}
