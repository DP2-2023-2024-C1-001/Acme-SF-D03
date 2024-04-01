
package acme.features.auditor.codeaudits;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.codeaudit.CodeAudit;
import acme.entities.project.Project;

@Repository
public interface AuditorCodeAuditRepository extends AbstractRepository {

	@Query("select ca from CodeAudit ca where ca.auditor.id = :auditorId")
	Collection<CodeAudit> findAllCodeAuditByAuditorId(int auditorId);

	@Query("select ca from CodeAudit ca where ca.id = :id")
	CodeAudit findOneCodeAuditById(int id);

	@Query("select ca.project from CodeAudit ca where ca.id = :id ")
	Collection<Project> findProjectByCodeAuditId(int id);
}
