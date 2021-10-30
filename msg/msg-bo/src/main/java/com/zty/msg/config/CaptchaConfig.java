package com.zty.msg.config;

import com.github.bingoohuang.patchca.color.ColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.FilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.DiffuseRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.DoubleRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.MarbleRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.WobbleRippleFilterFactory;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import java.awt.Color;
import java.security.SecureRandom;
import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaptchaConfig {
    public CaptchaConfig() {
    }

    @Bean
    public ConfigurableCaptchaService captchaService() {
        ConfigurableCaptchaService captchaService = new ConfigurableCaptchaService();
        ColorFactory colorFactory = new ColorFactory() {
            Random random = new SecureRandom();

            public Color getColor(int x) {
                int[] c = new int[3];
                int i = this.random.nextInt(c.length);

                for(int fi = 0; fi < c.length; ++fi) {
                    if (fi == i) {
                        c[fi] = this.random.nextInt(71);
                    } else {
                        c[fi] = this.random.nextInt(256);
                    }
                }

                return new Color(c[0], c[1], c[2]);
            }
        };
        captchaService.setColorFactory(colorFactory);
        FilterFactory f1 = new CurvesRippleFilterFactory(colorFactory);
        FilterFactory f2 = new MarbleRippleFilterFactory();
        FilterFactory f3 = new DoubleRippleFilterFactory();
        FilterFactory f4 = new WobbleRippleFilterFactory();
        FilterFactory f5 = new DiffuseRippleFilterFactory();
        captchaService.setFilterFactory((imgSource) -> {
            Random random = new SecureRandom();
            FilterFactory filterFactory;
            switch(random.nextInt(4)) {
                case 0:
                    filterFactory = f1;
                    break;
                case 1:
                    filterFactory = f2;
                    break;
                case 2:
                    filterFactory = f3;
                    break;
                case 3:
                    filterFactory = f4;
                    break;
                default:
                    filterFactory = f5;
            }

            return filterFactory.applyFilters(imgSource);
        });
        captchaService.setWidth(160);
        captchaService.setHeight(70);
        RandomWordFactory wordFactory = new RandomWordFactory();
        wordFactory.setMaxLength(4);
        wordFactory.setMinLength(4);
        wordFactory.setCharacters("234678ABCEFGHLMNPQRSTUVWXYZ");
        captchaService.setWordFactory(wordFactory);
        return captchaService;
    }
}
