package dyc.mybatis.simple.model;

/**
 * @author dengyichao
 * @Title: Country
 * @Package: dyc.mybatis.simple.model
 * @Description:
 * @date 2018/4/25  14:58
 */
public class Country {
    private Long id;
    private String countryName;
    private String countryCode;

    public Country() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
