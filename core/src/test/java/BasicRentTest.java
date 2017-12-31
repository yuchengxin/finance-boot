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

//    @Ignore
    @Test
    public void testBasicRentCalculation(){
        List<Merchant> signer = new ArrayList<>();
        Merchant merA = new Merchant.Builder()
                .merchantName("张三")
                .merchantPhone("1399xxxx297")
                .merchantIdNo("42112219999904")
                .bankInfo("汉口银行")
                .bankAccount("62302903548928")
                .merchantAddress("武汉市东西湖区吴家山街")
                .build();

        Merchant merB = new Merchant.Builder()
                .merchantName("程某")
                .merchantPhone("1869xxxx668")
                .merchantIdNo("421122219990")
                .bankInfo("汉口银行")
                .bankAccount("6223250033067")
                .merchantAddress("武汉市东西湖区吴家山街")
                .build();

        ReqResult<Merchant> result1 = merchantMgr.createMerchant(merA);
        Assert.assertTrue(result1.isSuccess());
        Assert.assertTrue(result1.getResult() != null);

        ReqResult<Merchant> result2 = merchantMgr.createMerchant(merB);
        Assert.assertTrue(result2.isSuccess());
        Assert.assertTrue(result2.getResult() != null);

        signer.add(result1.getResult());
        signer.add(result2.getResult());

        Contract contract = new Contract.Builder()
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
                .signer(signer)
                .subscriptionDate(DateUtils.parseDate("1997-07-30"))
                .leasebackPrice(4877620)
                .paybackDate(DateUtils.parseDate("2015-11-30"))
                .payStartDate(DateUtils.parseDate("2018-11-29"))
                .beneficiary(result1.getResult())
                .tariff(0.01)
                .taxAmount(4000)
                .logs("/home/yuankui/tmp/log.log")
                .proposalId(1)
                .contractStatus(ContractStatus.PENDINGRENTAL)
                .build();
        Contract newContract = contractMgr.createContract(contract).getResult();

        BasicRentMonthResult monthResult = basicRentMgr.calBasicRentMonth(newContract.getId(), DateUtils.parseDatetime("2018-12-1")).getResult();
        System.out.println(monthResult);

//        BasicRentYearResult yearResult = basicRentMgr.calBasicRentYear(newContract.getId(), 2025).getResult();
//        System.out.println(yearResult);
//
//        BasicRentPeriodResult periodResult = basicRentMgr.calBasicRentPeriod(newContract.getId(), 3).getResult();
//        System.out.println(periodResult);
//
//        BasicRentResult allResult = basicRentMgr.calBasicRentDetail(newContract.getId()).getResult();
//        System.out.println(allResult);
    }
}
