# 1130822 影音製作人才培訓班 LINE Bot

---

## 功能

### 學員管理

- 離訓(當日**開始**排除在系統之外)

### 行事曆

- 假日
- 課程
- 值日生(每日兩位)
	- 當有離訓者時自動重排值日生
- 補課

### 值日生們

- 從今日起開始到結訓日當天的值日生

#### 互換請求

:::info
甲：欲換班者
乙：甲欲換班的對象
:::

1. (Richmenu)甲提出申請
1. (Reply/Carousel Template Message)從今天開始到最後一天的值日生，扣除自己及當天的值日生，不重複
1. (Quick Reply)甲選擇乙
	- 第一頁
	- 上一頁
	- 下一頁
	- 最後頁
3. (Push/Text Message)乙收到通知
4. (Confirm Template Message)乙同意或拒絕
5. (Push/Text Message)甲收到通知

#### 缺席互換

:::info
甲：缺席者者
乙：欲代班者
丙：缺席者(甲)的當日小夥伴(另一名值日生)
:::

1. (Richmenu)乙提出申請
1. (Reply/Carousel Template Message)今天的值日生們
1. (Quick Reply)乙選擇甲
	- 第一頁
	- 上一頁
	- 下一頁
	- 最後頁
1. (Push/Text Message)丙收到通知
1. (Confirm Template Message)丙確認或取消
1. (Push/Text Message)甲收到通知

## 數據庫結構

### 學員(表)

![學員(表)](https://hackmd.io/_uploads/H1WG7y_60.png)

| 欄位名稱   | 資料型態      | 可否為空 | 唯一 | 默認    | 鍵  | 實體屬性       |
| ---------- | ------------- | -------- | ---- | ------- | --- | -------------- |
| `id`       | `int8`        | 🚫       | ✅   |         | 🔑  | `id`           |
| `shi_chuo` | `timestamptz` | 🚫       |      | `now()` |     | `occurredAt`   |
| `line_id`  | `char(33)`    | 🚫       | ✅   |         |     | `lineId`       |
| `zuo_hao`  | `int2`        | 🚫       | ✅   |         |     | `seatNumber`   |
| `li_xun`   | `date`        | ⭕️      |      |         |     | `droppedOutOn` |

#### DDL

```sql
CREATE TABLE IF NOT EXISTS "xue_yuan" (
	"id" int8 PRIMARY KEY,
	"le_guan_suo" int8,
	"shi_chuo" timestamptz NOT NULL DEFAULT "now"(),
	"line_id" char(33) NOT NULL UNIQUE,
	"zuo_hao" int2 NOT NULL UNIQUE,
	"li_xun" date
);
COMMENT ON TABLE "xue_yuan" IS '學員';
COMMENT ON COLUMN "xue_yuan"."id" IS '主鍵';
COMMENT ON COLUMN "xue_yuan"."shi_chuo" IS '時戳';
COMMENT ON COLUMN "xue_yuan"."line_id" IS 'LINE 用戶 ID';
COMMENT ON COLUMN "xue_yuan"."zuo_hao" IS '座號';
COMMENT ON COLUMN "xue_yuan"."li_xun" IS '離訓日';
```

### 休假類型(枚舉)

![休假類型(枚舉)](https://hackmd.io/_uploads/ryZbqQ1dTA.png)

### 行事曆(表)

![行事曆(表)](https://hackmd.io/_uploads/HyjZN1_T0.png)

| 欄位名稱         | 資料型態      | 可否為空 | 唯一 | 默認    | 鍵  | 實體屬性         |
| ---------------- | ------------- | -------- | ---- | ------- | --- | ---------------- |
| `id`             | `int8`        | 🚫       | ✅   |         | 🔑  | `id`             |
| `ri_li_lei_xing` | `varchar`     | 🚫       |      |         |     | `calendarType`   |
| `shi_jian`       | `varchar`     | 🚫       |      |         |     | `event`          |
| `chun_jie`       | `bool`        | 🚫       |      | `false` |     | `springFestival` |
| `kai_shi`        | `timestamptz` | 🚫       |      |         |     | `since`          |
| `jie_shu`        | `timestamptz` | 🚫       |      |         |     | `until`          |

#### DDL

```sql
CREATE TABLE IF NOT EXISTS "xing_shi_li" (
	"id" int8 PRIMARY KEY,
	"le_guan_suo" int8,
	"ri_li_lei_xing" varchar NOT NULL,
	"shi_jian" varchar NOT NULL,
	"chun_jie" bool NOT NULL DEFAULT '0',
	"kai_shi" timestamptz NOT NULL,
	"jie_shu" timestamptz NOT NULL
);
COMMENT ON TABLE "xing_shi_li" IS '行事曆';
COMMENT ON COLUMN "xing_shi_li"."id" IS '主鍵';
COMMENT ON COLUMN "xing_shi_li"."ri_li_lei_xing" IS '日曆類型';
COMMENT ON COLUMN "xing_shi_li"."shi_jian" IS '事件';
COMMENT ON COLUMN "xing_shi_li"."chun_jie" IS '春節';
COMMENT ON COLUMN "xing_shi_li"."kai_shi" IS '開始';
COMMENT ON COLUMN "xing_shi_li"."jie_shu" IS '結束';
```

### 換值類型(枚舉)

![換值類型(枚舉)](https://hackmd.io/_uploads/HJizLJ_6R.png)

### 值日生(表)

![值日生(表)](https://hackmd.io/_uploads/rkeOvk_p0.png)

| 欄位名稱            | 資料型態      | 可否為空 | 唯一 | 默認 | 鍵            | 實體屬性          |
| ------------------- | ------------- | -------- | ---- | ---- | ------------- | ----------------- |
| `id`                | `int8`        | 🚫       | ✅   |      | 🔑            | `id`              |
| `huan_zhi_lei_xing` | `varchar`     | ⭕️      |      |      |               | `shiftChangeType` |
| `xing_shi_li`       | `int8`        | 🚫       |      | 複合 | `xing_shi_li` | `courseCalendar`  |
| `xue_yuan`          | `timestamptz` | 🚫       |      | 複合 | `xue_yuan`    | `student`         |

#### DDL

```sql
CREATE TABLE IF NOT EXISTS "zhi_ri_sheng" (
	"id" int8 PRIMARY KEY,
	"le_guan_suo" int8,
	"huan_zhi_lei_xing" varchar NOT NULL,
	"xing_shi_li" int8 NOT NULL
		REFERENCES "xing_shi_li"("id")
		ON DELETE RESTRICT
		ON UPDATE CASCADE,
	"xue_yuan" int8 NOT NULL
		REFERENCES "xue_yuan"("id")
		ON DELETE RESTRICT
		ON UPDATE CASCADE,
);
COMMENT ON TABLE "zhi_ri_sheng" IS '值日生';
COMMENT ON COLUMN "zhi_ri_sheng"."id" IS '主鍵';
COMMENT ON COLUMN "zhi_ri_sheng"."huan_zhi_lei_xing" IS '換值類型';
COMMENT ON COLUMN "zhi_ri_sheng"."xing_shi_li" IS '行事曆';
COMMENT ON COLUMN "zhi_ri_sheng"."xue_yuan" IS '學員';
```
