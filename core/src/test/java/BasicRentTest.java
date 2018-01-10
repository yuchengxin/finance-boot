import com.gilab.wjj.core.FinanceCoreBootConfig;
import com.gilab.wjj.core.basicrent.BasicRentManager;
import com.gilab.wjj.core.contract.ContractManager;
import com.gilab.wjj.core.merchant.MerchantManager;
import com.gilab.wjj.core.proposal.ProposalManager;
import com.gilab.wjj.persistence.model.*;
import com.gilab.wjj.util.DateUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yuankui on 12/22/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FinanceCoreBootConfig.class})
public class BasicRentTest {
    @Autowired
    private ContractManager contractMgr;

    @Autowired
    private MerchantManager merchantMgr;

    @Autowired
    private ProposalManager proposalMgr;

    @Autowired
    private BasicRentManager basicRentMgr;

    @Test
    public void testCreateMerchants(){
        Merchant merA = new Merchant.Builder()
                .merchantName("张三")
                .merchantPhone("1399xxxx297")
                .merchantIdNo("42112219999904")
                .bankInfo("汉口银行A")
                .bankAccount("62302903548928")
                .merchantAddress("武汉市东西湖区吴家山街A")
                .build();

        Merchant merB = new Merchant.Builder()
                .merchantName("李四")
                .merchantPhone("1869xxxx668")
                .merchantIdNo("421122219990")
                .bankInfo("汉口银行B")
                .bankAccount("6223250033067")
                .merchantAddress("武汉市东西湖区吴家山街B")
                .build();

        Merchant merC = new Merchant.Builder()
                .merchantName("王五")
                .merchantPhone("1306xxxx321")
                .merchantIdNo("42112219999905")
                .bankInfo("汉口银行C")
                .bankAccount("62302903548929")
                .merchantAddress("武汉市东西湖区吴家山街C")
                .build();

        Merchant merD = new Merchant.Builder()
                .merchantName("袁七")
                .merchantPhone("1869xxxx669")
                .merchantIdNo("4211222199906")
                .bankInfo("汉口银行D")
                .bankAccount("6223250033830")
                .merchantAddress("武汉市东西湖区吴家山街D")
                .build();

        Merchant merE = new Merchant.Builder()
                .merchantName("赵钱")
                .merchantPhone("1399xxxx670")
                .merchantIdNo("42112219999907")
                .bankInfo("汉口银行E")
                .bankAccount("62302903548931")
                .merchantAddress("武汉市东西湖区吴家山街E")
                .build();

        Merchant merF = new Merchant.Builder()
                .merchantName("孙李")
                .merchantPhone("1869xxxx671")
                .merchantIdNo("4211222199908")
                .bankInfo("汉口银行F")
                .bankAccount("6223250033932")
                .merchantAddress("武汉市东西湖区吴家山街F")
                .build();

        ReqResult<Merchant> result1 = merchantMgr.createMerchant(merA);
        Assert.assertTrue(result1.isSuccess());
        Assert.assertTrue(result1.getResult() != null);

        ReqResult<Merchant> result2 = merchantMgr.createMerchant(merB);
        Assert.assertTrue(result2.isSuccess());
        Assert.assertTrue(result2.getResult() != null);

        ReqResult<Merchant> result3 = merchantMgr.createMerchant(merC);
        Assert.assertTrue(result3.isSuccess());
        Assert.assertTrue(result3.getResult() != null);

        ReqResult<Merchant> result4 = merchantMgr.createMerchant(merD);
        Assert.assertTrue(result4.isSuccess());
        Assert.assertTrue(result4.getResult() != null);

        ReqResult<Merchant> result5 = merchantMgr.createMerchant(merE);
        Assert.assertTrue(result5.isSuccess());
        Assert.assertTrue(result5.getResult() != null);

        ReqResult<Merchant> result6 = merchantMgr.createMerchant(merF);
        Assert.assertTrue(result6.isSuccess());
        Assert.assertTrue(result6.getResult() != null);
    }

    @Test
    public void testCreateContract(){
        List<Merchant> merchants = merchantMgr.getMerchantWithFilter(null, null, null, null);
        Assert.assertTrue(!merchants.isEmpty());

        Contract contract1 = new Contract.Builder()
                .contractNo("NO01")
                .contractVersion("1")
                .region("22")
                .contractTerDate(DateUtils.parseDate("2030-11-29"))
                .backPremium(123)
                .buildingInfo("1层-商10")
                .buildingSize(221.71)
                .originalPrice(5200)
                .totalPrice(6000)
                .signTotalPrice(5500)
                .signingDate(DateUtils.parseDate("2015-08-24"))
                .signingMode(SigningMode.MORTGAGE)
                .signer(Collections.singletonList(merchants.get(0)))
                .subscriptionDate(DateUtils.parseDate("1997-07-30"))
                .leasebackPrice(4877620)
                .paybackDate(DateUtils.parseDate("2015-11-30"))
                .payStartDate(DateUtils.parseDate("2018-11-29"))
                .beneficiary(merchants.get(0))
                .logs("/home/yuankui/tmp/log1.log")
                .proposalId(1L)
                .contractStatus(ContractStatus.PENDINGRENTAL)
                .build();

        Contract contract2 = new Contract.Builder()
                .contractNo("NO02")
                .contractVersion("2")
                .region("22")
                .contractTerDate(DateUtils.parseDate("2025-11-29"))
                .backPremium(123)
                .buildingInfo("1层-商10")
                .buildingSize(221.71)
                .originalPrice(5200)
                .totalPrice(6000)
                .signTotalPrice(5500)
                .signingDate(DateUtils.parseDate("2010-08-24"))
                .signingMode(SigningMode.MORTGAGE)
                .signer(Collections.singletonList(merchants.get(1)))
                .subscriptionDate(DateUtils.parseDate("1994-07-30"))
                .leasebackPrice(4877620)
                .paybackDate(DateUtils.parseDate("2010-11-30"))
                .payStartDate(DateUtils.parseDate("2013-11-29"))
                .beneficiary(merchants.get(1))
                .logs("/home/yuankui/tmp/log2.log")
                .proposalId(1L)
                .contractStatus(ContractStatus.PENDINGRENTAL)
                .build();

        Contract contract3 = new Contract.Builder()
                .contractNo("NO01")
                .contractVersion("3")
                .region("22")
                .contractTerDate(DateUtils.parseDate("2023-11-29"))
                .backPremium(123)
                .buildingInfo("1层-商10")
                .buildingSize(221.71)
                .originalPrice(5200)
                .totalPrice(6000)
                .signTotalPrice(5500)
                .signingDate(DateUtils.parseDate("2008-08-24"))
                .signingMode(SigningMode.MORTGAGE)
                .signer(Collections.singletonList(merchants.get(2)))
                .subscriptionDate(DateUtils.parseDate("1993-07-30"))
                .leasebackPrice(4877620)
                .paybackDate(DateUtils.parseDate("2008-11-30"))
                .payStartDate(DateUtils.parseDate("2011-11-29"))
                .beneficiary(merchants.get(2))
                .logs("/home/yuankui/tmp/log3.log")
                .proposalId(1L)
                .contractStatus(ContractStatus.RENTAL)
                .build();

        Contract contract4 = new Contract.Builder()
                .contractNo("NO01")
                .contractVersion("4")
                .region("22")
                .contractTerDate(DateUtils.parseDate("2010-11-29"))
                .backPremium(123)
                .buildingInfo("1层-商10")
                .buildingSize(221.71)
                .originalPrice(5200)
                .totalPrice(6000)
                .signTotalPrice(5500)
                .signingDate(DateUtils.parseDate("1995-08-24"))
                .signingMode(SigningMode.MORTGAGE)
                .signer(Collections.singletonList(merchants.get(3)))
                .subscriptionDate(DateUtils.parseDate("1992-07-30"))
                .leasebackPrice(4877620)
                .paybackDate(DateUtils.parseDate("1995-11-30"))
                .payStartDate(DateUtils.parseDate("1998-11-29"))
                .beneficiary(merchants.get(3))
                .logs("/home/yuankui/tmp/log4.log")
                .proposalId(1L)
                .contractStatus(ContractStatus.NORMALEND)
                .build();

        Contract contract5 = new Contract.Builder()
                .contractNo("NO01")
                .contractVersion("1")
                .region("22")
                .contractTerDate(DateUtils.parseDate("2030-11-29"))
                .backPremium(123)
                .buildingInfo("1层-商10")
                .buildingSize(221.71)
                .originalPrice(5200)
                .totalPrice(6000)
                .signTotalPrice(5500)
                .signingDate(DateUtils.parseDate("2015-08-24"))
                .signingMode(SigningMode.MORTGAGE)
                .signer(Collections.singletonList(merchants.get(4)))
                .subscriptionDate(DateUtils.parseDate("1997-07-30"))
                .leasebackPrice(4877620)
                .paybackDate(DateUtils.parseDate("2015-11-30"))
                .payStartDate(DateUtils.parseDate("2018-11-29"))
                .beneficiary(merchants.get(4))
                .logs("/home/yuankui/tmp/log5.log")
                .proposalId(1L)
                .contractStatus(ContractStatus.ABNORMALEND)
                .build();

        Contract contract6 = new Contract.Builder()
                .contractNo("NO06")
                .contractVersion("2")
                .region("22")
                .contractTerDate(DateUtils.parseDate("2030-11-29"))
                .backPremium(123)
                .buildingInfo("1层-商10")
                .buildingSize(221.71)
                .originalPrice(5200)
                .totalPrice(6000)
                .signTotalPrice(5500)
                .signingDate(DateUtils.parseDate("2015-08-24"))
                .signingMode(SigningMode.MORTGAGE)
                .signer(Collections.singletonList(merchants.get(5)))
                .subscriptionDate(DateUtils.parseDate("1997-07-30"))
                .leasebackPrice(4877620)
                .paybackDate(DateUtils.parseDate("2015-11-30"))
                .payStartDate(DateUtils.parseDate("2018-11-29"))
                .beneficiary(merchants.get(5))
                .logs("/home/yuankui/tmp/log6.log")
                .proposalId(1L)
                .contractStatus(ContractStatus.UNSTARTED)
                .build();


        Contract newContract1 = contractMgr.createContract(contract1).getResult();
        Assert.assertTrue(newContract1 != null);

        Contract newContract2 = contractMgr.createContract(contract2).getResult();
        Assert.assertTrue(newContract2 != null);

        Contract newContract3 = contractMgr.createContract(contract3).getResult();
        Assert.assertTrue(newContract3 != null);

        Contract newContract4 = contractMgr.createContract(contract4).getResult();
        Assert.assertTrue(newContract4 != null);

        Contract newContract5 = contractMgr.createContract(contract5).getResult();
        Assert.assertTrue(newContract5 != null);

        Contract newContract6 = contractMgr.createContract(contract6).getResult();
        Assert.assertTrue(newContract6 != null);


    }

    @Ignore
    @Test
    public void testBasicRentCalculation(){

        Contract newContract = contractMgr.getContract(1).getResult();

        BasicRentMonthResult monthResult = basicRentMgr.calBasicRentMonth(newContract.getId(), DateUtils.parseDatetime("2018-12-1")).getResult();
        System.out.println(monthResult);

        BasicRentYearResult yearResult = basicRentMgr.calBasicRentYear(newContract.getId(), 2025).getResult();
        System.out.println(yearResult);

        BasicRentPeriodResult periodResult = basicRentMgr.calBasicRentPeriod(newContract.getId(), 3).getResult();
        System.out.println(periodResult);

        BasicRentResult allResult = basicRentMgr.calBasicRentDetail(newContract.getId()).getResult();
        System.out.println(allResult);
    }
}
