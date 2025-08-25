package dev.guisandroni.warehouse.Service;

import dev.guisandroni.warehouse.Dto.StockStatusMessage;

public interface ProductChangeAvailability {

    void notifyStatusChange(final StockStatusMessage message);
}
