package com.gilab.wjj.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Created by yuankui on 11/1/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@SpringBootApplication(scanBasePackages = { "com.gilab.wjj" })
@EnableTransactionManagement
public class FinanceCoreBootConfig {
}
