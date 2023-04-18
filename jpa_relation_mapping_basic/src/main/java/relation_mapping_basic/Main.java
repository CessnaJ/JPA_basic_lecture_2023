package relation_mapping_basic;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_relation_mapping_basic");
        // persistence unit and EMF will be connected.

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //save
            Team team = new Team();
            team.setName("TeamA");
//            team.getMembers().add(member);
            // according to relational mapping, this should work.
            em.persist(team);



            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team); // host of relational mapping
            em.persist(member);

            team.addMember(member);

            em.flush();
            em.clear();

            //search
            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            for (Member m : members) {
                System.out.println("m="+ m.getUsername());
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();


    }
}