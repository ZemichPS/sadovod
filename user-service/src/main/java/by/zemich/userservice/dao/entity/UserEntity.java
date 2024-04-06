package by.zemich.userservice.dao.entity;


import by.zemich.userservice.core.UserRole;
import by.zemich.userservice.core.UserStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "users")
public class UserEntity {
    @Id
    private UUID uuid;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dtCreate;
    @UpdateTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    @Version
    private Timestamp dtUpdate;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String viberChannelApiKey;
    private String telegramUsername;

    public UserEntity(UUID uuid,
                      Timestamp dtCreate,
                      Timestamp dtUpdate,
                      String name,
                      String email,
                      String password,
                      UserRole role,
                      UserStatus status,
                      String viberChannelApiKey,
                      String telegramUsername) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.viberChannelApiKey = viberChannelApiKey;
        this.telegramUsername = telegramUsername;
    }

    public UserEntity() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Timestamp getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Timestamp dtCreate) {
        this.dtCreate = dtCreate;
    }

    public Timestamp getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(Timestamp dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getViberChannelApiKey() {
        return viberChannelApiKey;
    }

    public void setViberChannelApiKey(String viberChannelApiKey) {
        this.viberChannelApiKey = viberChannelApiKey;
    }

    public String getTelegramUsername() {
        return telegramUsername;
    }

    public void setTelegramUsername(String telegramUsername) {
        this.telegramUsername = telegramUsername;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
