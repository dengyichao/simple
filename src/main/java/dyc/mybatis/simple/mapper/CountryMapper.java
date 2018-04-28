package dyc.mybatis.simple.mapper;

import dyc.mybatis.simple.model.Country;

import java.util.List;

/**
 * @author dengyichao
 * @Title: CountryMapper
 * @Package: dyc.mybatis.simple.mapper
 * @Description:
 * @date 2018/4/25  15:00
 */
public interface CountryMapper {
    public List<Country> All();
}
