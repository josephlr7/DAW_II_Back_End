package cibertec.pe.client;

import cibertec.pe.dto.PaymentDto;

public interface IOrderPaymentClient {
    String procesarPago(PaymentDto paymentDto);
}
