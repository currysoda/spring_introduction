package com.hello.hello_spring.repository;

import com.hello.hello_spring.domain.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

// JDBC 를 이용한 예제 요즘은 JPA 를 사용해서 이렇게 하지 않음

public class JdbcMemberRepository implements MemberRepository {
	
	// JDBC 개발에서 필요한 것
	private final DataSource dataSource;
	
	@Autowired
	public JdbcMemberRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public Member save(Member member) {
		String            sql   = "insert into member(name) values(?)";
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, member.getName());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				member.setId(rs.getLong(1));
			}
			else {
				throw new SQLException("id 조회 실패");
			}
			return member;
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
		finally {
			close(conn, pstmt, rs);
		}
	}
	
	@Override
	public Optional<Member> findById(Long id) {
		String            sql   = "select * from member where id = ?";
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setName(rs.getString("name"));
				return Optional.of(member);
			}
			else {
				return Optional.empty();
			}
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
		finally {
			close(conn, pstmt, rs);
		}
	}
	
	@Override
	public List<Member> findAll() {
		String            sql   = "select * from member";
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Member> members = new ArrayList<>();
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setName(rs.getString("name"));
				members.add(member);
			}
			return members;
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
		finally {
			close(conn, pstmt, rs);
		}
	}
	
	@Override
	public void clearStore() {
	
	}
	
	@Override
	public Optional<Member> findByName(String name) {
		String            sql   = "select * from member where name = ?";
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setName(rs.getString("name"));
				return Optional.of(member);
			}
			return Optional.empty();
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
		finally {
			close(conn, pstmt, rs);
		}
	}
	
	private Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}
	
	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				close(conn);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void close(Connection conn) throws SQLException {
		DataSourceUtils.releaseConnection(conn, dataSource);
	}
}

