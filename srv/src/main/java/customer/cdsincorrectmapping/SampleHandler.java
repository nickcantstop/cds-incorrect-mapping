
package customer.cdsincorrectmapping;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.outbox.OutboxService;

import cds.gen.sample.Bar;
import cds.gen.sample.BarContext;
import cds.gen.sample.OnFooEventContext;
import cds.gen.sample.Sample;
import cds.gen.sample.Sample_;

@Component
@ServiceName(Sample_.CDS_NAME)
public class SampleHandler implements EventHandler {

    private final Sample sampleService;
    private final OutboxService outboxService;

    public SampleHandler(Sample sampleService, @Qualifier("ServiceOutbox") OutboxService outboxService) {
        this.sampleService = sampleService;
        this.outboxService = outboxService;
    }

    @On(service = Sample_.CDS_NAME, event = OnFooEventContext.CDS_NAME)
    public void onMySample(OnFooEventContext context) {

        BarContext barContext = BarContext.create();

        Bar bar = Bar.create();
        bar.setActionId(context.getData().getActionId());
        barContext.setData(bar);

        outboxService.outboxed(sampleService).emit(barContext);
        context.setCompleted();
    }

    @On(service = Sample_.CDS_NAME, event = BarContext.CDS_NAME)
    public void onMySample(BarContext context) {

        System.out.println("Fails Here ↓");
        context.getData().getActionId();

        context.setCompleted();
    }

}