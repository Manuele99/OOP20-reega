package reega.statistics;

import org.apache.commons.lang3.tuple.Pair;
import reega.data.models.Contract;
import reega.data.models.Data;
import reega.data.models.ServiceType;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StatisticsController {
    void setData(List<Data> data);
    /**
     * Get the peek usage
     * @param svcType service type used to get the average usage
     * @return the date of the peek usage and the value of the peek
     */
    Optional<Pair<Date,Double>> getPeek(ServiceType svcType);

    /**
     * Get the average usage
     * @param svcType service type used to get the average usage
     * @return the average usage
     */
    double getAverageUsage(ServiceType svcType);

    /**
     * Get the total usage
     * @param svcType service type used to get the total usage
     * @return the total usage
     */
    double getTotalUsage(ServiceType svcType);
}