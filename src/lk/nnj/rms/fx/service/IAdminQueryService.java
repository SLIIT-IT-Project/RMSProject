package lk.nnj.rms.fx.service;

import java.time.LocalDate;

public interface IAdminQueryService {
    int findTotalOrders (LocalDate d1, LocalDate d2) throws Exception;
    double findTotalSales(LocalDate d1, LocalDate d2) throws Exception;
    int findTotalItems(LocalDate d1, LocalDate d2) throws Exception;
    int findTotalCustomers(LocalDate d1, LocalDate d2) throws Exception;
    int findTotalSalesPerDay(LocalDate d1) throws Exception;
    int findDineInSalesPerDay(LocalDate d1) throws Exception;
    int findTakeAwaySalesPerDay(LocalDate d1) throws Exception;
    int findDeliverSalesPerDay(LocalDate d1) throws Exception;
}
