package org.nervos.huobi.service.admission_control;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nervos.huobi.service.admission_control.type.AddressList;
import org.nervos.huobi.service.admission_control.type.NewAdmin;
import org.nervos.huobi.service.admission_control.type.StatusList;
import org.nervos.muta.Muta;

@AllArgsConstructor
@Getter
public class AdmissionControlService {
    private final Muta muta;

    public static final String SERVICE_NAME = "admission_control";
    public static final String METHOD_STATUS = "status";
    public static final String METHOD_CHANGE_ADMIN = "change_admin";
    public static final String METHOD_FORBID = "forbid";
    public static final String METHOD_PERMIT = "permit";

    public StatusList status(AddressList addressList) throws IOException {
        StatusList statusList =
                muta.queryService(
                        SERVICE_NAME,
                        METHOD_STATUS,
                        addressList,
                        new TypeReference<StatusList>() {});
        return statusList;
    }

    public void change_admin(NewAdmin newAdmin) throws IOException {
        muta.sendTransactionAndPollResult(
                SERVICE_NAME, METHOD_CHANGE_ADMIN, newAdmin, new TypeReference<Void>() {});
    }

    public void forbid(AddressList addressList) throws IOException {
        muta.sendTransactionAndPollResult(
                SERVICE_NAME, METHOD_FORBID, addressList, new TypeReference<Void>() {});
    }

    public void permit(AddressList addressList) throws IOException {
        muta.sendTransactionAndPollResult(
                SERVICE_NAME, METHOD_PERMIT, addressList, new TypeReference<Void>() {});
    }
}
