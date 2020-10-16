package lk.nnj.rms.fx.service;

import java.time.LocalDate;

public interface IAdminQueryService {
    int findTotalOrders (LocalDate d1, LocalDate d2) throws Exception;
    double findTotalSales(LocalDate d1, LocalDate d2) throws Exception;
    int findTotalItems(LocalDate d1, LocalDate d2) throws Exception;
    int findTotalCustomers(LocalDate d1, LocalDate d2) throws Exception;
    double findTotalSalesPerDay(LocalDate d1) throws Exception;
    double findDineInSalesPerDay(LocalDate d1) throws Exception;
    double findTakeAwaySalesPerDay(LocalDate d1) throws Exception;
    double findDeliverSalesPerDay(LocalDate d1) throws Exception;
}
