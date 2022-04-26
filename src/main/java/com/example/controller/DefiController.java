package com.example.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.example.model.Chami;
import com.example.model.Defi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin
@RequestMapping("/api/defis")
public class DefiController {

    @Autowired
    private DataSource dataSource;

    // 404 si pas de slash
    @GetMapping("/")
    public ArrayList<Defi> allDefis(HttpServletResponse response) throws SQLException {

        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM lesDefis D JOIN lesChamis C ON C.login = D.auteur");

            ArrayList<Defi> lesDefis = new ArrayList<Defi>();

            while (rs.next()) {
                String id = rs.getString("id");
                String titre = rs.getString("titre");
                Timestamp timeStamp = rs.getTimestamp("dateDeCreation");
                LocalDateTime dateDeCreation = timeStamp.toLocalDateTime();
                String description = rs.getString("description");
                String login = rs.getString("login");
                int age = rs.getInt("age");

                Chami chami = new Chami(login, age);
                Defi d = new Defi(id, titre, dateDeCreation, description, chami);

                lesDefis.add(d);
            }
            return lesDefis;

        } catch (Exception e) {
            response.setStatus(500);
            try {
                response.getOutputStream().print(e.getMessage());
            } catch (Exception e2) {
                System.err.println(e2.getMessage());
            }
            return null;
        }
    }

    @GetMapping("/{defiId}")
    public Defi read(@PathVariable(value = "defiId") String id, HttpServletResponse response) throws SQLException {
        
        try (Connection connection = dataSource.getConnection()) {
            // Statement stmt = connection.createStatement();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lesDefis D JOIN lesChamis C ON C.login = D.auteur WHERE id = ? ");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String idDefi = rs.getString("id");
                String titre = rs.getString("titre");
                Timestamp timeStamp = rs.getTimestamp("dateDeCreation");
                LocalDateTime dateDeCreation = timeStamp.toLocalDateTime();
                String description = rs.getString("description");
                String login = rs.getString("login");
                int age = rs.getInt("age");

                Chami chami = new Chami(login, age);
                Defi defi  = new Defi(idDefi, titre, dateDeCreation, description, chami);

                return defi;

            } else {
                response.setStatus(404);
                try {
                    response.getOutputStream().print("Le défi n'existe pas");
                } catch (Exception e2) {
                    System.err.println(e2.getMessage());
                }
                return null;

            }

        } catch (Exception e) {
            // throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
            response.setStatus(405);
            try {
                response.getOutputStream().print(e.getMessage());
            } catch (Exception e2) {
                System.err.println(e2.getMessage());
            }
            return null;
        }

    }

    @PostMapping("/{defiId}")
    public Defi create(@PathVariable(value = "defiId") String id, @RequestBody Defi defi, HttpServletResponse response) throws SQLException {
        if (id.equals(defi.getId())) {
            try (Connection connection = dataSource.getConnection()) {
                // on verifie quil nexiste pas d'abord
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lesDefis WHERE id = ? ");
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();
                if (!rs.next()) {
                    PreparedStatement stmtInsert = connection
                            .prepareStatement("INSERT INTO lesDefis (id, titre, dateDeCreation, description, auteur) VALUES (?,?,?,?,?)");
                    stmtInsert.setString(1, defi.getId());
                    stmtInsert.setString(2, defi.getTitre());
                    stmtInsert.setTimestamp(3, Timestamp.valueOf(defi.getDateDeCreation()));
                    stmtInsert.setString(4, defi.getDescription());
                    stmtInsert.setString(5, defi.getAuteur().getLogin());
                    
                    boolean row = stmtInsert.execute();
                    
                    return defi;
                } else {
                    response.setStatus(403);
                    try {
                        response.getOutputStream().print("Le défi existe déjà");
                    } catch (Exception e2) {
                        System.err.println(e2.getMessage());
                    }
                    return null;
                }
            } catch (Exception e) {
                response.setStatus(405);
                System.err.println(e.getMessage());
                // try{
                // response.getOutputStream().print("Le défi existe déjà");
                // }catch(Exception e2){
                // System.err.println(e2.getMessage());
                // }
                return null;
            }
        } else {
            response.setStatus(412);
            try {
                response.getOutputStream().print("L'id du défi ne correspond pas");
            } catch (Exception e2) {
                System.err.println(e2.getMessage());
            }
            return null;
        }
    }

    @PutMapping("/{defiId}")
    public Defi update(@PathVariable(value = "defiId") String id, @RequestBody Defi defi,
            HttpServletResponse response) {
        if (id.equals(defi.getId())) {
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lesDefis WHERE id = ? ");
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    PreparedStatement stmtPut = connection
                            .prepareStatement("UPDATE lesDefis SET id = ?, titre = ?, dateDeCreation = ?, description = ?, auteur = ? WHERE id = ?");
                    stmtPut.setString(1, defi.getId());
                    stmtPut.setString(2, defi.getTitre());
                    stmtPut.setTimestamp(3, Timestamp.valueOf(defi.getDateDeCreation()));
                    stmtPut.setString(4, defi.getDescription());
                    stmtPut.setString(5, defi.getAuteur().getLogin());
                    stmtPut.setString(6, id);
                    stmtPut.execute();
                    return defi;
                } else {
                    response.setStatus(404);
                    try {
                        response.getOutputStream().print("Le défi n'existe pas");
                    } catch (Exception e2) {
                        System.err.println(e2.getMessage());
                    }
                    return null;
                }
            } catch (Exception e) {
                response.setStatus(405);
                try {
                    response.getOutputStream().print(e.getMessage());
                } catch (Exception e2) {
                    System.err.println(e2.getMessage());
                }
                return null;
            }
        } else {
            response.setStatus(412);
            try {
                response.getOutputStream().print("Le défi est different");
            } catch (Exception e2) {
                System.err.println(e2.getMessage());
            }
            return null;
        }
    }

    @DeleteMapping("/{defiId}")
    public void delete(@PathVariable(value = "defiId") String id, HttpServletResponse response) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lesDefis WHERE id = ? ");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {                
                PreparedStatement stmtPut = connection.prepareStatement("DELETE FROM lesDefis WHERE id = ?");
                stmtPut.setString(1, id);
                stmtPut.execute();
            } else {
                response.setStatus(404);
                try {
                    response.getOutputStream().print("Le défi n'existe pas");
                } catch (Exception e2) {
                    System.err.println(e2.getMessage());
                }
            }
        } catch (Exception e) {
            response.setStatus(405);
            try {
                response.getOutputStream().print(e.getMessage());
            } catch (Exception e2) {
                System.err.println(e2.getMessage());
            }
        }
    }
}
