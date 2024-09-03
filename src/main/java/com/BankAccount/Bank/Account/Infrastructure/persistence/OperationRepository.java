package com.BankAccount.Bank.Account.Infrastructure.persistence;

import com.BankAccount.Bank.Account.Domain.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query(value = "SELECT * FROM operation " +
            "WHERE operation.numerocompte = :nCompte " +
            "AND operation.typecompte = :typeCompte " +
            "AND operation.dateoperation BETWEEN :dateDebut AND :dateFin " +
            "ORDER BY operation.dateOperation DESC", nativeQuery = true)
    List<Operation> findOperationForRelever(@Param("nCompte") String nCompte,
                                            @Param("typeCompte") String typeCompte,
                                            @Param("dateDebut") LocalDate dateDebut,
                                            @Param("dateFin") LocalDate dateFin);
}
