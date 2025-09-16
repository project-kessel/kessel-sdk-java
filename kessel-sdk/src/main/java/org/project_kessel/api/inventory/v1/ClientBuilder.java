package org.project_kessel.api.inventory.v1;

import io.grpc.Channel;
import org.project_kessel.api.inventory.AbstractClientBuilder;

import static org.project_kessel.api.inventory.v1.KesselInventoryHealthServiceGrpc.*;

public class ClientBuilder extends AbstractClientBuilder<KesselInventoryHealthServiceBlockingStub, KesselInventoryHealthServiceStub> {
    public ClientBuilder(String target) {
        super(target);
    }

    @Override
    protected KesselInventoryHealthServiceBlockingStub newStub(Channel channel) {
        return KesselInventoryHealthServiceGrpc.newBlockingStub(channel);
    }

    @Override
    protected KesselInventoryHealthServiceStub newAsyncStub(Channel channel) {
        return KesselInventoryHealthServiceGrpc.newStub(channel);
    }
}
