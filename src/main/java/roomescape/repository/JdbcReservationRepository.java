package roomescape.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import roomescape.domain.Reservation;
import roomescape.repository.rowMapper.ReservationRowMapper;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcReservationRepository {

    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    public JdbcReservationRepository(JdbcTemplate template, DataSource source) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(source)
                .withTableName("reservation")
                .usingGeneratedKeyColumns("id");
    }

    public List<Reservation> findAll() {
        List<Reservation> reservations = template.query("select * from reservation",
                new ReservationRowMapper());

        return reservations;
    }


    public Long save(Reservation newReservation) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(newReservation);

        return insert.executeAndReturnKey(params).longValue();
    }


    public Reservation findById(Long id) {
        Reservation reservation = template.queryForObject("select * from reservation where id = ?",
                new ReservationRowMapper(),
                id);

        return reservation;
    }

    public void deleteById(Long id) {
        template.update("delete from reservation where id = ?", id);
    }
}
