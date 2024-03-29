package reega.data.models.gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import reega.data.models.ServiceType;
import reega.main.Settings;

public class NewContract {
    @SerializedName("address")
    public String address;
    @SerializedName("services")
    public String services;
    @SerializedName("user_fiscal_code")
    public String userFiscalCode;
    @SerializedName("start_time")
    public String startTime;

    public NewContract(final String address, final List<ServiceType> services, final String userFiscalCode,
            final Date startTime) {
        this.address = address;
        this.services = new Gson().toJson(services.stream().map(ServiceType::getName).collect(Collectors.toList()));
        this.userFiscalCode = userFiscalCode;
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        format.setTimeZone(TimeZone.getTimeZone(Settings.DB_TIMEZONE));
        this.startTime = format.format(startTime);
    }
}
