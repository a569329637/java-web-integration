package com.souche.shiroweb.service;

import com.souche.shiroweb.entity.Organization;

import java.util.List;

/**
 * Created by Administrator on 2017/10/21.
 */
public interface OrganizationService {

    public Organization createOrganization(Organization organization);
    public Organization updateOrganization(Organization organization);
    public void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);
    List<Organization> findAll();

    Object findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);

}
