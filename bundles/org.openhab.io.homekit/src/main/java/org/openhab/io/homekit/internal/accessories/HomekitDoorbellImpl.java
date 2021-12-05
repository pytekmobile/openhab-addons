package org.openhab.io.homekit.internal.accessories;

import io.github.hapjava.accessories.DoorbellAccessory;
import io.github.hapjava.accessories.optionalcharacteristic.AccessoryWithName;
import io.github.hapjava.characteristics.HomekitCharacteristicChangeCallback;
import io.github.hapjava.characteristics.impl.common.ProgrammableSwitchEnum;
import io.github.hapjava.services.impl.DoorbellService;
import org.openhab.core.items.GenericItem;
import org.openhab.io.homekit.internal.HomekitAccessoryUpdater;
import org.openhab.io.homekit.internal.HomekitSettings;
import org.openhab.io.homekit.internal.HomekitTaggedItem;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implements Doorbell using an Item that provides a SINGLE_PRESS state.
 *
 * @author Stefan Puehringer - Initial contribution
 */
public class HomekitDoorbellImpl extends AbstractHomekitAccessoryImpl implements DoorbellAccessory, AccessoryWithName {

    public HomekitDoorbellImpl(HomekitTaggedItem accessory, List<HomekitTaggedItem> characteristics,
                               HomekitAccessoryUpdater updater, HomekitSettings settings) {
        super(accessory, characteristics, updater, settings);
        getServices().add(new DoorbellService(this));
    }

    @Override
    public CompletableFuture<String> getName() {
        return CompletableFuture.completedFuture("Stefans Doorbell");
    }

    @Override
    public CompletableFuture<String> getModel() {
        return CompletableFuture.completedFuture("Shelly Uni");
    }

    @Override
    public CompletableFuture<ProgrammableSwitchEnum> getSwitchEvent() {
        return CompletableFuture.completedFuture(ProgrammableSwitchEnum.SINGLE_PRESS);
        // According to hapJava IP devices should return null but this causes a NullPointerException
        // return CompletableFuture.completedFuture(null);
    }

    @Override
    public void subscribeSwitchEvent(HomekitCharacteristicChangeCallback callback) {
        getUpdater().subscribe(((GenericItem) getRootAccessory().getItem()), callback);
    }

    @Override
    public void unsubscribeSwitchEvent() {
        getUpdater().unsubscribe(((GenericItem) getRootAccessory().getItem()));
    }
}
