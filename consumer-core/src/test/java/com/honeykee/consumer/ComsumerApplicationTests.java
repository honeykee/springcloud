package com.honeykee.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.honeykee.consumer.entity.TableBEntity;
import com.honeykee.consumer.repository.TableBRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComsumerApplicationTests {

    @Autowired
    TableBRepository tableBRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void contextLoads() {

        Specification specification ;
        List< TableBEntity > tableBRepositoryAll;
        try {
            //嵌套or条件查询
            specification = buildSpecification4();
            tableBRepositoryAll = tableBRepository.findAll(specification);
            System.out.println( mapper.writeValueAsString( tableBRepositoryAll ) );

            specification = buildSpecification3();
            tableBRepositoryAll = tableBRepository.findAll(specification);
            System.out.println( mapper.writeValueAsString( tableBRepositoryAll ) );

            specification = buildSpecification2();
            tableBRepositoryAll = tableBRepository.findAll(specification);
            System.out.println( mapper.writeValueAsString( tableBRepositoryAll ) );
        } catch ( JsonProcessingException e ) {
            e.printStackTrace();
        }
    }

    private Specification buildSpecification2() {
        return new Specification() {
            @Override
            public Predicate toPredicate( Root root, CriteriaQuery query, CriteriaBuilder builder ) {
                Predicate predicate = builder.conjunction();
//                Join<TableBEntity, Set<TableBEntity.Type> > join = root.join( "" );

                List< Expression< Boolean > > expressions = predicate.getExpressions();
                expressions.add( builder.equal( root.get( "id" ), 1 ) );
//                expressions.add( builder.isNull( root.get( "name" ) ) );
//                expressions.add( builder.isEmpty( root.get( "name" ) ) );//导致错误,这个方法不是使用在字段判断上的,参考:https://stackoverflow.com/questions/6340554/

                //方法一
                Predicate predicateOr = builder.or(
                        builder.equal( root.get( "name" ), "zcs" ),
                        builder.equal( root.get( "name" ), ""),
                        builder.isNull( root.<String>get( "name" ) )
                );

                //方法二
                List<Predicate> predicateList = new ArrayList<>(  );
                predicateList.add( builder.equal( root.get( "name" ), "zcs" ) );
                predicateList.add( builder.equal( root.get( "name" ) ,"") );
                predicateList.add( builder.isNull( root.get( "name" ) ) );
                Predicate[] predicateArr = new Predicate[predicateList.size()];
                Predicate predicateOr2 = builder.or( predicateList.toArray(predicateArr) );

                Predicate restriction = query.where( predicate ,predicateOr,predicateOr2 ).getRestriction();
                return restriction;

                //方法三,这个可以直接返回Predicate, (推荐)
//                expressions.add( builder.or(
//                        builder.equal(root.<String>get(  "name" ), "zcs"),
//                        builder.equal(root.<String>get( "name" ),"" ),
//                        builder.isNull(root.<String>get( "name" ) )
//                ) );
//                return predicate;
            }
        };
    }

    private Specification buildSpecification3() {
        return new Specification() {
            @Override
            public Predicate toPredicate( Root root, CriteriaQuery query, CriteriaBuilder builder ) {
                Predicate predicate = builder.conjunction();
                //方法五通过 disjunction
                Predicate disjunction = builder.disjunction();
                List< Expression< Boolean > > disExpression = disjunction.getExpressions();
                List< Expression< Boolean > > expressions = predicate.getExpressions();
                expressions.add( builder.equal( root.get( "id" ), 3 ) );

                disExpression.add( builder.isNull( root.get( "name" ) ) );
                disExpression.add( builder.equal( root.get( "name" ), "" ) );

                expressions.add( disjunction );
                return predicate;
            }
        };
    }

    private Specification buildSpecification4() {
        return new Specification() {
            @Override
            public Predicate toPredicate( Root root, CriteriaQuery query, CriteriaBuilder builder ) {
                Predicate predicate = builder.conjunction();

                List< Expression< Boolean > > expressions = predicate.getExpressions();

                //这个查不到 null 的值
                //select tablebenti0_.id as id1_1_, tablebenti0_.name as name2_1_ from table_b tablebenti0_ where tablebenti0_.name<>?
//                expressions.add( builder.equal( root.get( "name" ), "zcs" ).not() );
//                return predicate;

                //方法四
                predicate =  builder.or(
                        builder.equal(root.<String>get(  "name" ), "zcs"),
                        builder.equal(root.<String>get( "name" ),"" ),
                        builder.isNull(root.<String>get( "name" ) )
                ) ;

                predicate = builder.and( builder.equal(  root.get("id") , 1 ) , predicate  );

                return predicate;
            }
        };
    }


}
