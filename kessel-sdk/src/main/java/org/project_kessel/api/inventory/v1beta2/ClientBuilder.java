package org.project_kessel.api.inventory.v1beta2;

import io.grpc.Channel;
import org.project_kessel.api.inventory.AbstractClientBuilder;

import static org.project_kessel.api.inventory.v1beta2.KesselInventoryServiceGrpc.KesselInventoryServiceBlockingStub;
import static org.project_kessel.api.inventory.v1beta2.KesselInventoryServiceGrpc.KesselInventoryServiceStub;

public class ClientBuilder extends AbstractClientBuilder<KesselInventoryServiceBlockingStub, KesselInventoryServiceStub> {
    public ClientBuilder(String target) {
        super(target);
    }

    @Override
    protected KesselInventoryServiceBlockingStub newStub(Channel channel) {
        return KesselInventoryServiceGrpc.newBlockingStub(channel);
    }

    @Override
    protected KesselInventoryServiceStub newAsyncStub(Channel channel) {
        return KesselInventoryServiceGrpc.newStub(channel);
    }
}
