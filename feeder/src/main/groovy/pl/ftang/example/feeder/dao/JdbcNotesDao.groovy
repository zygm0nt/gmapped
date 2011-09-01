package pl.ftang.example.feeder.dao

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * User: mcl
 * Date: 3/7/11 8:44 AM
 */
class JdbcNotesDao extends JdbcDaoSupport implements NotesDao {

    public List getEvents(Long timestamp) {
        String query = """select n.*, cu.first_name, cu.last_name, addr.city, addr.street, addr.house_number, c.event_msisdn, c.event_case_id
            from some_table
        """
        
        List ret = getJdbcTemplate().query(query, new NotesRowMapper())
        ret
    }


}
