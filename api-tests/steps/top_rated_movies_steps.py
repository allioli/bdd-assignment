
import requests
from behave import given, when, then
from hamcrest import equal_to, assert_that, starts_with, less_than_or_equal_to, contains_string


api_key = '419af08f20d7174c0a764c55e22c403a'
base_movie_db_api_base_url = 'https://api.themoviedb.org/3/movie/'

def compose_mobiedb_request_url(context):

    url = base_movie_db_api_base_url + context.request_params['api_resource']

    if('custom_api_key' in context.request_params):
        url += ('?api_key=' + context.request_params['custom_api_key'])
    else:
        url += '?api_key=' + api_key

    if('language' in context.request_params):
        url += ('&language=' + context.request_params['language'])

    if('region' in context.request_params):
        url += ('&region=' + context.request_params['region'])
    
    if('page' in context.request_params):
        url += ('&page=' + context.request_params['page'])

    #print(url)

    return url


@given(u'I create moviedb API request for "{api_resource}"')
def step_given_I_create_moviedb_api_request(context, api_resource):

    context.request_params = {'api_resource': api_resource}


@given(u'I add parameter "{parameter_name}" with value "{parameter_value}"')
def step_given_I_add_parameter_with_value(context, parameter_name, parameter_value):

    context.request_params[parameter_name] = parameter_value


@when(u'I perform request to movie-db API')
def step_when_I_perform_request_to_movie_db_api(context):

    request_url = compose_mobiedb_request_url(context)
    context.response = requests.get(request_url)
    context.response_json = context.response.json()


@then(u'response code is "{response_code:d}"')
def step_then_response_code(context, response_code):

    assert_that(context.response.status_code, equal_to(response_code))


@then(u'response contains "{field}" equal to number "{value:g}"')
def step_then_response_contains_field_number(context, field, value):

    assert_that(context.response_json[field], equal_to(value))

@then(u'response contains "{field}" equal to "{value}"')
def step_then_response_contains_field(context, field, value):

    assert_that(context.response_json[field], equal_to(value))


@then(u'response contains movie in position "{index:d}" with "{field}" equal to number "{value:g}"')
def step_then_response_contains_movie_with_field_equal_number(context, index, field, value):

    assert_that(context.response_json['results'][index][field], equal_to(value))


@then(u'response contains movie in position "{index:d}" with "{field}" equal to "{value}"')
def step_then_response_contains_movie_with_field_equal(context, index, field, value):

    assert_that(context.response_json['results'][index][field], equal_to(value))


@then(u'response contains movie in position "{index:d}" with "{field}" starting with "{value}"')
def step_then_response_contains_movie_with_field_starting(context, index, field, value):

    assert_that(context.response_json['results'][index][field], starts_with(value))

@then(u'movies are sorted by "{field}" in descending order')
def step_then_movies_are_sorted_by_field_desc(context, field):

    num_results = len(context.response_json['results'])

    if (num_results > 1):
        for index in range(1, num_results):
            # Check that movie value for field is lower or equal than on the previous one
            movie_value = context.response_json['results'][index][field]
            previous_movie_value =  context.response_json['results'][index-1][field]
            assert_that(movie_value, less_than_or_equal_to(previous_movie_value), 'Movies not correctly sorted')

@then(u'response contains "{result_number:d}" results')
def step_then_response_contains_number_results(context, result_number):
    
    assert_that(len(context.response_json['results']), equal_to(result_number))

@then(u'response contains error message "{error_message}"')
def step_then_response_contains_error_message(context, error_message):
    
   # raise Exception('Movie with id: ' + context.response.text + ' MATCHES ' + error_message)

    if len(context.response_json['errors']) > 0:
        assert_that(context.response.text, contains_string(error_message))
     
    
  
