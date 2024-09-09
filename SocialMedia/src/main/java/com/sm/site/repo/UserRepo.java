package com.sm.site.repo;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sm.site.user.User;

@Repository
public interface UserRepo extends JpaRepository <User, Integer>, JpaSpecificationExecutor<User> {
	
		
		default boolean existById(Integer id) {
			return findOne((root,query,cb) -> cb.and(
					cb.equal(root.get("id"), id)
					)).isPresent();
			
		}
		
        
        default boolean existByEmail(String email) {
        	return findOne((root,query,em) -> em.and(
        			em.equal(root.get("email"), email)
        			)).isPresent();
        }

        
       
       
        
}
























