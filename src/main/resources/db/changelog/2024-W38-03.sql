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

CREATE TABLE IF NOT EXISTS "zhi_ri_sheng" (
	"id" int8 PRIMARY KEY,
	"le_guan_suo" int8,
	"huan_zhi_lei_xing" varchar,
	"xing_shi_li" int8 NOT NULL REFERENCES "xing_shi_li"("id") ON DELETE RESTRICT ON UPDATE CASCADE,
	"xue_yuan" int8 NOT NULL REFERENCES "xue_yuan"("id") ON DELETE RESTRICT ON UPDATE CASCADE,
	UNIQUE("xing_shi_li","xue_yuan")
);
COMMENT ON TABLE "zhi_ri_sheng" IS '值日生';
COMMENT ON COLUMN "zhi_ri_sheng"."id" IS '主鍵';
COMMENT ON COLUMN "zhi_ri_sheng"."huan_zhi_lei_xing" IS '換值類型';
COMMENT ON COLUMN "zhi_ri_sheng"."xing_shi_li" IS '行事曆';
COMMENT ON COLUMN "zhi_ri_sheng"."xue_yuan" IS '學員';
