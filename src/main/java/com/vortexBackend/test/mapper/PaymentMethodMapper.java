package com.vortexBackend.test.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.vortexBackend.test.domain.PaymentMethod;
import com.vortexBackend.test.dto.PaymentMethodDTO;


@Mapper
public interface PaymentMethodMapper {
	public PaymentMethodDTO toPaymentMethodDTO(PaymentMethod paymentMethod);

	public PaymentMethod toPaymentMethod(PaymentMethodDTO paymentMethodDTO);

	public List<PaymentMethodDTO> toPaymentMethodDTOs(List<PaymentMethod> paymentMethods);

	public List<PaymentMethod> toPaymentMethods(List<PaymentMethodDTO> paymentMethodDTOs);

}
