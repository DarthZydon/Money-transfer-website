package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.jboss.logging.BasicLogger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Transfer> findAll() {
        List<Transfer> transferList = new ArrayList<>();
        Transfer transfer = new Transfer();
        try {
            String sql = "SELECT * FROM transfer WHERE transfer IS NOT NULL";
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            if (rowSet.next()) {
                transfer = mapRowToTransfer(rowSet);
                transferList.add(transfer);
            }
        } catch (RestClientResponseException e) {
                System.out.println(e.getMessage());
            }
        return transferList;
    }

    @Override
    public Transfer getTransferByTransferId(Long transferId) {
        Transfer transfer = new Transfer();
        try {
            String sql = "SELECT * FROM transfer WHERE transfer_id = ?";
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, transferId);
            if (rowSet.next()) {
                return mapRowToTransfer(rowSet);
            }
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        } return null;
    }

    @Override
    public int findTransferIDbyUsername(String username) {
        return 0;
    }

    public List<Transfer> getTransfersByUserId(Long id) {
        List<Transfer> transferList = new ArrayList<>();
        Transfer transfer = new Transfer();

        try {
            String sql = "SELECT * FROM transfer\n" +
                    "WHERE account_from = (\n" +
                    "SELECT account_id FROM tenmo_user\n" +
                    "JOIN account ON account.user_id = tenmo_user.user_id\n" +
                    "WHERE account.user_id = ?\n" +
                    ")\n";
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
            if (rowSet.next()) {
                transfer = mapRowToTransfer(rowSet);
                transferList.add(transfer);
            }
        } catch (UsernameNotFoundException e) {
            System.out.println("Error: User " + id + " not found");
        } return transferList;
    }

    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();

        transfer.setTransferId(rs.getLong("transfer_id"));
        transfer.setTransferTypeId(rs.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rs.getInt("transfer_status_id"));
        transfer.setAccountFrom(rs.getLong("account_from"));
        transfer.setAccountTo(rs.getLong("account_to"));
        transfer.setAmount(BigDecimal.valueOf(rs.getDouble("amount")));

        return transfer;
    }
}
