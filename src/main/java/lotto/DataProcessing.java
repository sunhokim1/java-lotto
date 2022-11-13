package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class DataProcessing {
    public List<Integer> lottoRandomNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

    public List<Integer> sortLottoNumbers(List<Integer> sortNumbers) {
        Collections.sort(sortNumbers);
        return sortNumbers;
    }

    public List<List<Integer>> countCreateRandomNumbers(int count) {
        List<List<Integer>> boxNumbers = new ArrayList<>();
        for (int i=0;i<count;++i) {
            boxNumbers.add(sortLottoNumbers(lottoRandomNumbers()));
        }
        return boxNumbers;
    }

    public int conversionMoney(String money) {
        ErrorUtil errorUtil = new ErrorUtil();
        int conversion = Integer.parseInt(money);
        errorUtil.errorInputMoney(conversion);
        return conversion;
    }

    public int countLotto(int money) {
        return money / 1000;
    }

    public String[] splitLottoNumber(String lottoNumbers) {
        ErrorUtil errorUtil = new ErrorUtil();
        String[] splitNumbers = lottoNumbers.split(",");
        errorUtil.errorInputLottoNumber(splitNumbers);
        errorUtil.errorInputCountLottoNumber(splitNumbers);
        return splitNumbers;
    }

    public  List<Integer> countWinLotto(List<Integer> lottoNumber, List<List<Integer>> boxRandomNumber,
                                        int count, int bonusNumber) {
        List<Integer> countWinLotto = new ArrayList<>();
        for (int i=0;i<count;++i)
            countWinLotto.add(winLotto(lottoNumber, boxRandomNumber.get(i), bonusNumber));
        return countWinLotto;
    }
    public boolean secondWin(List<Integer> randomNumber, int bonusNumber) {
        return randomNumber.contains(bonusNumber);
    }
    public int winLotto(List<Integer> lottoNumber, List<Integer> randomNumber, int bonusNumber) {
        int win;
        Set<Integer> countWin = new HashSet<>(lottoNumber);
        countWin.retainAll(randomNumber);
        win = countWin.size();
        if (win == 5 && secondWin(randomNumber, bonusNumber))
            win = 7;
        return win;
    }

    public List<Integer> resultWinLotto(List<Integer> countWin) {
        List<Integer> resultLotto = new ArrayList<>();
        int sixWin = 0;
        int fiveWinBonusOne = 0;
        int fiveWin = 0;
        int fourWin = 0;
        int threeWin = 0;
        for (Integer integer : countWin) {
            if (integer == 6)
                sixWin += 1;
            else if (integer == 7)
                fiveWinBonusOne += 1;
            else if (integer == 5)
                fiveWin += 1;
            else if (integer == 4)
                fourWin += 1;
            else if (integer == 3)
                threeWin += 1;
        }
        resultLotto.add(threeWin);
        resultLotto.add(fourWin);
        resultLotto.add(fiveWin);
        resultLotto.add(fiveWinBonusOne);
        resultLotto.add(sixWin);
        return resultLotto;
    }
}