package springjpa.detailed.repository;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import springjpa.detailed.entity.Passport;

@Repository
public class PassportRepository {

	@PersistenceContext
	private EntityManager em;

	public Passport findById(Long id) {
		Passport p = em.find(Passport.class, id);
		return p;
	}
}
