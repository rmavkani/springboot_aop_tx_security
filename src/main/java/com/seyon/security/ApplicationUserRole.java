package com.seyon.security; 

import static com.seyon.security.ApplicationUserPermission.*;
import java.util.*;
import com.google.common.collect.Sets;

public enum ApplicationUserRole {
PATIENT(Sets.newHashSet(PATIENT_READ,PATIENT_WRITE)),
ADMIN(Sets.newHashSet()),
DOCTOR(Sets.newHashSet());

private final Set<ApplicationUserPermission> permissions;
  
ApplicationUserRole(Set<ApplicationUserPermission> permissions)  {
  this.permissions = permissions;
}

public Set<ApplicationUserPermission> getPersmissions(){
  return permissions; 
}

}