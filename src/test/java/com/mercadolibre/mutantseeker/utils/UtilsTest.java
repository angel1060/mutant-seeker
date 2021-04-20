package com.mercadolibre.mutantseeker.utils;

import com.mercadolibre.mutantseeker.data.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class UtilsTest {
    @Test
    void convertToListNxNTheResultSizeShouldbeSame() {
        List<List<String>> list = Utils.convertToListNxN(Data.REQUEST_MUTANT_ARRAY);
        Assertions.assertEquals(Data.REQUEST_MUTANT_ARRAY.length, list.size());
    }

    @Test
    void convertArrayToSimpleText() {
        String text = Utils.convertArrayToSimpleText(Data.REQUEST_MUTANT_ARRAY);
        Assertions.assertNotNull(text);
    }

    @Test
    void convertArrayToSimpleTextReturnWhiteWhenArrayIsEmpty() {
        String text = Utils.convertArrayToSimpleText(new String[]{});
        Assertions.assertEquals("", text);
    }

    @Test
    void reverseMatriz() {
        List<List<String>> list = Utils.reverseMatriz(Data.DNA_NOT_MUNTANT);
        List<List<String>> rerverseList = Utils.reverseMatriz(list);

        // El primer elemento de la primer fila de la lista original debe corresponder al último elemento de la lista reversa
        Assertions.assertEquals(list.get(0).get(0), rerverseList.get(0).get(rerverseList.size() - 1));
    }


}