package reega.statistics;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import reega.data.models.Data;
import reega.data.models.ServiceType;

public interface StatisticsController {
    /**
     * Set the current data.
     *
     * @param data data used for manipulation
     */
    void setData(List<Data> data);

    /**
     * Get the peek usage.
     *
     * @param svcType service type used to get the average usage
     * @return the date(day) of the peek usage and the value of the peek
     */
    Optional<Pair<Date, Double>> getPeek(ServiceType svcType);

    /**
     * Get the average usage by day.
     *
     * @param svcType service type used to get the average usage
     * @return the average usage by day
     */
    double getAverageUsage(ServiceType svcType);

    /**
     * Get the total usage by day.
     *
     * @param svcType service type used to get the total usage
     * @return the total usage by day
     */
    double getTotalUsage(ServiceType svcType);

    /**
     * Get the current data.
     *
     * @return the current data as a list of data
     */
    List<Data> getCurrentData();
}
