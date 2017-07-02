<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %><script type="text/x-template" id="act-form-template">
	<div v-bind:id="flag + '_index_' + seq">
		<div v-if="flag != 'skill'">
			<div class="form-group">
				<div class="col-sm-3"><h5>[{{seq + 1}}]</h5></div>
				<div class="col-sm-9 text-right">
					<i class="fa fa-times text-danger add-click" aria-hidden="true" title="삭제" v-on:click="item_remove(flag, seq)"></i>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">{{titles[flag].title}}</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" v-bind:name="flag + '[' + seq + '].title'" v-model="data.title" required/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">{{titles[flag].sub_title}}</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" v-bind:name="flag + '[' + seq + '].sub_title'" v-model="data.sub_title" required/>
				</div>
			</div>
			<div class="form-group" v-if="flag == 'project'">
				<label class="col-sm-3 control-label">URL</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" v-bind:name="flag + '[' + seq + '].related_site'" v-model="data.related_site"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">기간</label>
				<div class="col-sm-9">
					<div class="input-daterange input-group">
						<input type="text" class="input-sm form-control" v-bind:name="flag + '[' + seq + '].start_dt'" v-model="data.str_start_dt"/>
						<span class="input-group-addon">to</span>
						<input type="text" class="input-sm form-control" v-bind:name="flag + '[' + seq + '].end_dt'" v-model="data.str_end_dt"/>
					</div>
				</div>
			</div>
			<div class="form-group" v-if="flag != 'education'">
				<label class="col-sm-3 control-label">{{titles[flag].description}}</label>
				<div class="col-sm-9">
					<textarea class="form-control" v-bind:name="flag + '[' + seq + '].description'" v-model="data.description" rows="3"/>
				</div>
			</div>
			<div class="form-group" v-if="flag == 'project'">
				<label class="col-sm-3 control-label" style="padding-top: 0px;">사용기술</label>
				<div class="col-sm-9">
					<span v-for="(item, index) in (data.tech || '').split('/')" v-bind:key="item" v-on:click="tech_tag_remove(item, seq)"
					      v-if="index > 0" class="label label-primary add-click" style="margin-right: 2px;">{{item}} &times;</span>
					<span class="label label-success add-click" v-on:click="open_tech_tag_modal(seq)">
						<i class="fa fa-plus-circle" aria-hidden="true" title="추가"></i>
					</span>
					<input type="hidden" v-bind:name="'project[' + seq + '].tech'" v-model="data.tech"/>
				</div>
			</div>
		</div>
		<div class="row" v-if="flag == 'skill'">
			<div class="col-sm-3">
				<div class="input-group input-group-sm">
					<input type="text" class="form-control" v-bind:name="'skill[' + seq + '].title'" v-model="data.title" placeholder="기술명" required/>
				</div>
			</div>
			<div class="col-sm-7">
				<input type="range" class="form-control" v-bind:name="'skill[' + seq + '].level'" v-model="data.level"
				       v-bind:onchange="'skill' + seq + '_level.value = value'" min="0" max="100"/>
			</div>
			<div class="col-sm-2 text-right">
				<output v-bind:id="'skill' + seq + '_level'" style="display:inline-block">{{data.level}}</output>
				&nbsp;
				<i class="fa fa-times text-danger add-click" aria-hidden="true" title="삭제" v-on:click="item_remove(flag, seq)"></i>
			</div>
		</div>
	</div>
</script>