/*
 * Copyright 2019-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.basic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of data type. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step03DataTypeTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          Basic Type
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_datatype_basicType() {
        String sea = "mystic";
        Integer land = 416;
        // #1on1: 日付型の言葉のお話 (2026/09/04)
        // o 日付: yyyy/MM/dd (時分秒は含まない) // Date
        // o 日時: yyyy/MM/dd HH:mm:ss.SSS... // DateTime
        // 歴史的には、必ずしもこうならないこともあって...
        // java.util.Date: 1970年からの相対ミリ秒 → つまり時分秒を持っている
        // 抽象的な意味合いのDateという感じで使われることもあるので注意。
        // 相対ミリ秒自体はJava固有のものではなく、UNIX時間。
        // java.util.Date は、実装上の都合も色々あって置き換えられた。
        LocalDate piari = LocalDate.of(2001, 9, 4);
        LocalDateTime bonvo = LocalDateTime.of(2001, 9, 4, 12, 34, 56);
        Boolean dstore = true;
        BigDecimal amba = new BigDecimal("9.4");

        piari = piari.plusDays(1);
        land = piari.getYear();
        bonvo = bonvo.plusMonths(1); // takaharaメモ：2001-10-04-12:34:56
        land = bonvo.getMonthValue(); // takaharaメモ：10
        land--; // takaharaメモ：9
        if (dstore) {
            // takaharaメモ：trueなので実行される
            BigDecimal addedDecimal = amba.add(new BigDecimal(land)); // takaharaメモ：18.4
            sea = String.valueOf(addedDecimal); // takaharaメモ：18.4
        }
        log(sea); // your answer? => 18.4
    }

    // ===================================================================================
    //                                                                           Primitive
    //                                                                           =========
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_primitive() {
        byte sea = 127; // max
        short land = 32767; // max
        int piari = 1;
        long bonvo = 9223372036854775807L; // max
        float dstore = 1.1f;
        double amba = 2.3d;
        char miraco = 'a';
        boolean dohotel = miraco == 'a';
        if (dohotel && dstore >= piari) {
            bonvo = sea;
            land = (short) bonvo;
            bonvo = piari;
            sea = (byte) land;
            if (amba == 2.3D) {
                sea = (byte) amba; // seaが2になる。小数点以下が切り捨て
            }
        }
        if ((int) dstore > piari) {
            // takahara: 実行されない
            sea = 0;
        }
        log(sea); // your answer? => 2

        // #1on1: 教訓:言語の細かい挙動を知らないと読めないプログラムを極力書かない (2026/09/04)
        // 一度、ややこしいのを経験しておく思い出が大事。
    }

    // ===================================================================================
    //                                                                              Object
    //                                                                              ======
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_object() {
        St3ImmutableStage stage = new St3ImmutableStage("hangar");
        String sea = stage.getStageName();
        log(sea); // your answer? => hangar
    }

    private static class St3ImmutableStage {

        // #1on1: インスタンス変数には極力finalをつけよう話 (2026/09/04)
        private final String stageName;

        public St3ImmutableStage(String stageName) {
            this.stageName = stageName;
        }

        public String getStageName() {
            return stageName;
        }
    }
}
