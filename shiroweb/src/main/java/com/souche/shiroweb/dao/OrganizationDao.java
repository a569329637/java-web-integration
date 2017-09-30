package com.souche.shiroweb.dao;

import com.souche.shiroweb.entity.Organization;

import java.util.List;

public interface OrganizationDao {

    Organization createOrganization(Organization organization);
    Organization updateOrganization(Organization organization);
    void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);
    List<Organization> findAll();

    List<Organization> findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);

}
