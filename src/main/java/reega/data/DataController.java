package reega.data;

import org.jetbrains.annotations.Nullable;
import reega.data.models.Contract;
import reega.data.models.Data;
import reega.data.models.DataType;
import reega.data.models.gson.NewContract;
import reega.users.User;

import java.io.IOException;
import java.util.List;

/**
 * This controller handles all the data-based operations
 */
public interface DataController {

    /**
     * List all contracts for the user
     *
     * @return List<Contract>
     * @throws IOException
     */
    List<Contract> getUserContracts() throws IOException;

    /**
     * Find and return a user by a specified contract ID.
     * Admin only
     * @param contractID id of the contract
     * @return the accountholder user
     * @throws IOException
     */
    User getUserFromContract(int contractID) throws IOException;

    /**
     * retrieves all the contracts in the name of the specified user
     *
     * @param fiscalCode
     * @return
     * @throws IOException
     */
    List<Contract> getContractsForUser(String fiscalCode) throws IOException;

    /**
     * @return
     * @throws IOException
     */
    List<Contract> getAllContracts() throws IOException;

    /**
     * Add contract
     *
     * @param contract
     * @throws IOException
     */
    void addContract(NewContract contract) throws IOException;

    /**
     * Delete contract with REEGA. It will also delete all the related data
     *
     * @param id
     * @throws IOException
     */
    void removeContract(int id) throws IOException;

    /**
     * Push data to the database (implementation specific)
     *
     * @param data
     */
    void putUserData(Data data) throws IOException;

    /**
     * Get the latest timestamp for the specific contract and metric present in the database
     *
     * @param contractID
     * @return
     */
    Long getLatestData(int contractID, DataType service) throws IOException;

    List<Data> getMonthlyData(@Nullable Integer contractID) throws IOException;
}
