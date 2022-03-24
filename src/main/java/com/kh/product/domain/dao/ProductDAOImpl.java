package com.kh.product.domain.dao;

import com.kh.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {

    private final JdbcTemplate jdbcTemplate;

    //등록
    @Override
    public Product insert(Product product) {

        StringBuffer sql = new StringBuffer();
        sql.append(" insert into product (product_num,product_name,product_cnt,product_pri) ");
        sql.append(" values(product_product_num_seq.nextval, ?, ? , ? ) ");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        sql.toString(),
                        new String[] {"product_num"}

                );
                pstmt.setString(1,product.getProduct_name());
                pstmt.setLong(2,product.getProduct_cnt());
                pstmt.setLong(3,product.getProduct_pri());

                return pstmt;
            }
        },keyHolder);

        long product_num = keyHolder.getKey().longValue();

        return findByProduct_num(product_num);
    }

    //전체조회
    @Override
    public List<Product> findAll() {

        StringBuffer sql = new StringBuffer();
        sql.append(" select product_num , ");
        sql.append("        product_name, ");
        sql.append("        product_cnt, ");
        sql.append("        product_pri ");
        sql.append(" from   product ");

        List<Product> products =jdbcTemplate.query(sql.toString(),
                new BeanPropertyRowMapper<>(Product.class));

        return products;
    }

    //조회
    @Override
    public Product findByProduct_num(Long product_num) {

        StringBuffer sql = new StringBuffer();
        sql.append(" select product_num , ");
        sql.append("        product_name, ");
        sql.append("        product_cnt, ");
        sql.append("        product_pri ");
        sql.append(" from   product ");
        sql.append(" where  product_num = ? ");

        Product product = null;

        try {
            product = jdbcTemplate.queryForObject(
                    sql.toString(),
                    new BeanPropertyRowMapper<>(Product.class),
                    product_num);

        } catch (Exception e) {
            product = null;
        }

        return product;
    }

    //삭제
    @Override
    public int delete(Long product_num) {

        StringBuffer sql = new StringBuffer();
        sql.append(" DELETE product ");
        sql.append(" WHERE product_num = ? ");

        int delProduct = jdbcTemplate.update(sql.toString(),product_num);

        return delProduct;
    }

    //수정
    @Override
    public Product update(Long product_num, Product product) {
        StringBuffer sql = new StringBuffer();

        sql.append(" update product ");
        sql.append(" set product_name = ? , ");
        sql.append("         product_cnt = ? , ");
        sql.append("         product_pri = ? ");
        sql.append(" where product_num = ? ");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        sql.toString(),
                        new String[] {"product_num"}

                );
                pstmt.setString(1,product.getProduct_name());
                pstmt.setLong(2,product.getProduct_cnt());
                pstmt.setLong(3,product.getProduct_pri());
                pstmt.setLong(4,product.getProduct_num());

                return pstmt;
            }
        },keyHolder);

        product_num = keyHolder.getKey().longValue();

        return findByProduct_num(product_num);
    }

}
