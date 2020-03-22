package com.spring.jdbc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.spring.jdbc.model.Sale;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class SalesDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Sale> list() {
		
		String query = "SELECT * FROM SALES";
		List<Sale> sales = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Sale.class));
		return sales;
	}

	public void save(Sale sale) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("SALES").usingColumns("item", "quantity", "amount");
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(sale);
		insert.execute(params);
	}

	public Sale get(int id) {
		String query = "SELECT * FROM SALES WHERE ID = ?";
		Object[] args = {id};
		Sale sale = jdbcTemplate.queryForObject(query, args, BeanPropertyRowMapper.newInstance(Sale.class));
		return sale;
	}

	public void update(Sale sale) {
		String query = "UPDATE SALES SET item=:item, quantity=:quantity, amount=:amount WHERE id=:id";
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(sale);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.update(query, params);
	}

	public void delete(int id) {
		String query = "DELETE FROM SALES WHERE id = ?";
		jdbcTemplate.update(query,id);
	}
}
