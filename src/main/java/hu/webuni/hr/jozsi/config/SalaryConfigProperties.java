package hu.webuni.hr.jozsi.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hr.salary")
public class SalaryConfigProperties {
    private List<Double> limits;
    private List<Integer> percentages;

    public List<Double> getLimits() { return limits; }
    public void setLimits(List<Double> limits) { this.limits = limits; }
    public List<Integer> getPercentages() { return percentages; }
    public void setPercentages(List<Integer> percentages) { this.percentages = percentages; }
}