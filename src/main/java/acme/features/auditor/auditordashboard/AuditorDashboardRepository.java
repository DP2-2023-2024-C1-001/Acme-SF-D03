
package acme.features.auditor.auditordashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface AuditorDashboardRepository extends AbstractRepository {

	@Query("select count(ca) from CodeAudit ca where ca.auditor.id = :auditorId AND ca.type = acme.entities.codeaudit.CodeAuditType.STATIC")
	Integer totalNumberOfCodeAuditsWithTypeStatic(int auditorId);

	@Query("select count(ca) from CodeAudit ca where ca.auditor.id = :auditorId AND ca.type = acme.entities.codeaudit.CodeAuditType.DYNAMIC")
	Integer totalNumberOfCodeAuditsWithTypeDynamic(int auditorId);

	//	@Query("select avg(ar) from AuditRecord ar where ar.codeAudit.auditor.id = :auditorId")
	//	Double averageNumberOfAuditRecords(int auditorId);
	//
	//	@Query("select stddev(ar) from AuditRecord ar where ar.codeAudit.auditor.id = :auditorId")
	//	Double deviationNumberOfAuditRecords(int auditorId);
	//
	//	@Query("select min(ar) from AuditRecord ar where ar.codeAudit.auditor.id = :auditorId")
	//	Integer minimumNumberOfAuditRecords(int auditorId);
	//
	//	@Query("select max(ar) from AuditRecord ar where ar.codeAudit.auditor.id = :auditorId")
	//	Integer maximumNumberOfAuditRecords(int auditorId);

	@Query("select avg(ar.totalTime) from AuditRecord ar where ar.codeAudit.auditor.id = :auditorId")
	Double averageTimeOfPeriodLegthOfAuditRecord(int auditorId);

	@Query("select stddev(ar.totalTime) from AuditRecord ar where ar.codeAudit.auditor.id = :auditorId")
	Double deviationTimeOfPeriodLegthOfAuditRecord(int auditorId);

	@Query("select min(ar.totalTime) from AuditRecord ar where ar.codeAudit.auditor.id = :auditorId")
	Integer minimumTimeOfPeriodLegthOfAuditRecord(int auditorId);

	@Query("select max(ar.totalTime) from AuditRecord ar where ar.codeAudit.auditor.id = :auditorId")
	Integer maximumTimeOfPeriodLegthOfAuditRecord(int auditorId);

	//	@Query("select ar.periodStart, ar.periodEnd from AuditRecord ar where ar.codeAudit.auditor.id = :auditorId")
	//	Collection<Object[]> findPeriodStartAndPeriodEnd(int auditorId);
	//
	//	default double averageTimeOfPeriodLegthOfAuditRecord(final Collection<Object[]> objects) {
	//		List<Double> periods = new ArrayList<>();
	//		for (Object[] date : objects) {
	//			Date periodStart = (Date) date[0];
	//			Date periodEnd = (Date) date[1];
	//			double diferencia = (periodEnd.getTime() - periodStart.getTime()) / (1000 * 60 * 60);
	//			periods.add(diferencia);
	//		}
	//	}
}
