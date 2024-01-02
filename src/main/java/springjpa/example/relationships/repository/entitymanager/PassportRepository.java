package springjpa.example.relationships.repository.entitymanager;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import springjpa.example.relationships.entity.Passport;

@Repository
public class PassportRepository {

	@PersistenceContext
	private EntityManager em;

	public Passport findById(Long id) {
		Passport p = em.find(Passport.class, id);
		return p;
	}
}
