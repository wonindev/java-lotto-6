package lotto.UI;

import lotto.constants.ErrorMessage;
import lotto.constants.LottoEnum;
import lotto.utils.CommonHelper;
import lotto.utils.Validator;

import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputHandler {
    public String inputCost() {
        String input;

        while (true) {
            try {
                System.out.println("구입금액을 입력해주세요.");
                input = readLine();
                if (costValidate(input)) break;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return input;
    }

    public List<String> inputWinningNumbers() {
        String input;
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                input = readLine();
                winningNumberValidate(input);
                if (splitedLottoNumbersValidate(input)) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return CommonHelper.splitComma(input);
    }

    public String inputBonusNumber() {
        String input;

        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                input = readLine();
                if (numberValidate(input)) break;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return input;
    }

    private boolean costValidate(String input) {
        Validator.isNull(input);
        Validator.isBlank(input);
        Validator.isContainBlank(input);
        Validator.notNumber(input);
        Validator.underByLottoPrice(input);
        Validator.isDivisibleByLottoSize(input);
        return true;
    }

    private boolean winningNumberValidate(String input) {
        Validator.isNull(input);
        Validator.isBlank(input);
        Validator.isContainBlank(input);
        return true;
    }

    private boolean numberValidate(String input) {
        Validator.isNull(input);
        Validator.isBlank(input);
        Validator.isContainBlank(input);
        Validator.notNumber(input);
        Validator.isOneToFortyFive(Integer.parseInt(input));
        return true;
    }

    public boolean splitedLottoNumbersValidate(String input) {
        List<String> splitedLottoNumbers = List.of(input.split(","));

        if (splitedLottoNumbers.size() != LottoEnum.LOTTO_SIZE.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_SIX_NUMBER_ERROR.getError());
        }

        for (int i = 0; i < LottoEnum.LOTTO_SIZE.getValue(); i++) {
            numberValidate(splitedLottoNumbers.get(i));
            Validator.isDuplicate(splitedLottoNumbers, splitedLottoNumbers.get(i), i);
        }
        return true;
    }
}
