package com.zza.antiepidemic;


import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableMPP
@SpringBootApplication
public class AntiepidemicApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntiepidemicApplication.class, args);
    }

}
