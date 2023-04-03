package com.edhec.commonedhecapps.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

    /**
     * The persistent class for the USERS database table.
     *
     */
    @Entity
    @Table(schema = "CAS", name="USERS")
    public class User implements Serializable, UserDetails {
        private static final long serialVersionUID = 1L;

        @Id
        private String username;

        @Column(name="AD_REFERENCE")
        private String adReference;

        @Column(name="CNT_ID")
        private String cntId;

        @Column(name="CREATED_BY")
        private String createdBy;

        @Temporal( TemporalType.DATE)
        @Column(name="DATE_CREATED")
        private Date dateCreated;

        @Temporal( TemporalType.DATE)
        @Column(name="DATE_MODIFIED")
        private Date dateModified;

        private String email;

        private String enabled;

        @Column(name="FAD_ID")
        private String fadId;

        @Column(name="FIRST_NAME")
        private String firstName;

        @Column(name="INTERNAL_REFERENCE")
        private String internalReference;

        @Column(name="LAST_NAME")
        private String lastName;

        @Column(name="LIVE_ID")
        private String liveId;

        @Column(name="LIVE_PASSWORD")
        private String livePassword;

        private String login;

        @Column(name="MODIFIED_BY")
        private String modifiedBy;

        private String password;

        private String type;

        @Column(name="WEB_CODE")
        private String webCode;

        //bi-directional many-to-one association to GroupMember
        @OneToMany(mappedBy="user",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @Fetch(FetchMode.SUBSELECT)
        private List<GroupMember> groupMembers;

        public User() {
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAdReference() {
            return this.adReference;
        }

        public void setAdReference(String adReference) {
            this.adReference = adReference;
        }

        public String getCntId() {
            return this.cntId;
        }

        public void setCntId(String cntId) {
            this.cntId = cntId;
        }

        public String getCreatedBy() {
            return this.createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public Date getDateCreated() {
            return this.dateCreated;
        }

        public void setDateCreated(Date dateCreated) {
            this.dateCreated = dateCreated;
        }

        public Date getDateModified() {
            return this.dateModified;
        }

        public void setDateModified(Date dateModified) {
            this.dateModified = dateModified;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEnabled() {
            return this.enabled;
        }

        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }

        public String getFadId() {
            return this.fadId;
        }

        public void setFadId(String fadId) {
            this.fadId = fadId;
        }

        public String getFirstName() {
            return this.firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getInternalReference() {
            return this.internalReference;
        }

        public void setInternalReference(String internalReference) {
            this.internalReference = internalReference;
        }

        public String getLastName() {
            return this.lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getLiveId() {
            return this.liveId;
        }

        public void setLiveId(String liveId) {
            this.liveId = liveId;
        }

        public String getLivePassword() {
            return this.livePassword;
        }

        public void setLivePassword(String livePassword) {
            this.livePassword = livePassword;
        }

        public String getLogin() {
            return this.login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getModifiedBy() {
            return this.modifiedBy;
        }

        public void setModifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWebCode() {
            return this.webCode;
        }

        public void setWebCode(String webCode) {
            this.webCode = webCode;
        }

        public List<GroupMember> getGroupMembers() {
            return this.groupMembers;
        }

        public void setGroupMembers(List<GroupMember> groupMembers) {
            this.groupMembers = groupMembers;
        }

        public boolean isAccountNonExpired() {
            return this.getEnabled().equalsIgnoreCase("1")?true:false;
        }

        public boolean isAccountNonLocked() {
            return this.getEnabled().equalsIgnoreCase("1")?true:false;
        }

        public boolean isCredentialsNonExpired() {
            // TODO Auto-generated method stub
            return true;
        }

        public boolean isEnabled() {
            return this.getEnabled().equalsIgnoreCase("1")?true:false;
        }

        public Collection<GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
            for (GroupMember groupMember : groupMembers) {
                if(groupMember.getGroupAssociated() != null && groupMember.getGroupAssociated().getGroupAuthorities() != null) {
                    for (GroupAuthority groupAuthority : groupMember.getGroupAssociated().getGroupAuthorities()) {
                        if(groupAuthority.getId().getAuthority() != null) {
                            //list.add(new GrantedAuthorityImpl(groupAuthority.getId().getAuthority()));
                            list.add(new SimpleGrantedAuthority(groupAuthority.getId().getAuthority()));

                        }
                    }
                }
            }
            return list;
        }

        public String getNameCapitalized() {
            return StringUtils.capitalize(this.firstName.toLowerCase()) + " " + StringUtils.capitalize(this.lastName.toLowerCase());
        }

    }
